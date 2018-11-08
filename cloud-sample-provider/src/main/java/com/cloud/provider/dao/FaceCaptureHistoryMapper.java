package com.cloud.provider.dao;

import com.cloud.provider.bean.FaceCaptureHistory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FaceCaptureHistoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table face_capture_history
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table face_capture_history
     *
     * @mbg.generated
     */
    int insert(FaceCaptureHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table face_capture_history
     *
     * @mbg.generated
     */
    int insertSelective(FaceCaptureHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table face_capture_history
     *
     * @mbg.generated
     */
    FaceCaptureHistory selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table face_capture_history
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(FaceCaptureHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table face_capture_history
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(FaceCaptureHistory record);
}