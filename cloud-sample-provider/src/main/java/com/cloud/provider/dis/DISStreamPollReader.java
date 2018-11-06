package com.cloud.provider.dis;

import com.huaweicloud.dis.DIS;
import com.huaweicloud.dis.iface.data.request.GetPartitionCursorRequest;
import com.huaweicloud.dis.iface.data.request.GetRecordsRequest;
import com.huaweicloud.dis.iface.data.response.GetPartitionCursorResult;
import com.huaweicloud.dis.iface.data.response.GetRecordsResult;
import com.huaweicloud.dis.iface.data.response.Record;
import com.huaweicloud.dis.util.PartitionCursorTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName DISStreamPollReader
 * @Description 以轮询的方式读取DIS流
 * @Author Hyx
 * @DATE 2018/11/1 10:44
 */
public class DISStreamPollReader implements DISStreamReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(DISStreamPollReader.class);
    private DISStreamCollector disStreamCollector;
    /**
     * 配置数据下载分区ID
     */
    private String partitionId = "0";

    /**
     *  配置下载数据方式
     *  AT_SEQUENCE_NUMBER 从指定的sequenceNumber开始获取，需要设置StartingSequenceNumber
     *  AFTER_SEQUENCE_NUMBER 从指定的sequenceNumber之后开始获取，需要设置StartingSequenceNumber
     *  TRIM_HORIZON 从最旧的记录开始获取
     *  LATEST 从最新的记录开始获取
     *  AT_TIMESTAMP 从指定的时间戳(13位)开始获取，需要设置Timestamp
     */
    private String cursorType = PartitionCursorTypeEnum.AT_TIMESTAMP.name();

    /**
     * 配置每次最多获取的条数
     */
    private int limit = 10000;


    @Override
    public void reader(DIS dis, String streamName){
        Executor executor =  new ThreadPoolExecutor(1,5,60L, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        executor.execute(()->{
            LOGGER.info("start read stream from {}",streamName);
            GetPartitionCursorRequest request = new GetPartitionCursorRequest();
            request.setStreamName(streamName);
            request.setPartitionId(partitionId);
            request.setTimestamp(System.currentTimeMillis());
            request.setCursorType(cursorType);
            GetPartitionCursorResult response = dis.getPartitionCursor(request);
            String cursor = response.getPartitionCursor();
            LOGGER.info("Get stream {}[partitionId={}] cursor success : {}", streamName, partitionId, cursor);
            GetRecordsRequest recordsRequest = new GetRecordsRequest();
            while(true){
                recordsRequest.setPartitionCursor(cursor);
                recordsRequest.setLimit(limit);
                GetRecordsResult recordResponse = dis.getRecords(recordsRequest);
                // 下一批数据游标
                cursor = recordResponse.getNextPartitionCursor();
                List<Record> records = recordResponse.getRecords();
                Iterator<Record> iterator = records.iterator();
                while (iterator.hasNext()){
                    Record record = iterator.next();
                    collection(record);
                }
                try {
                    Thread.sleep(1000);
                }catch (Exception e){

                }
            }
        });
    }


    private void collection(Record record) {
        disStreamCollector.collection(record);
    }

    public void setDisStreamCollector(DISStreamCollector disStreamCollector) {
        this.disStreamCollector = disStreamCollector;
    }
}