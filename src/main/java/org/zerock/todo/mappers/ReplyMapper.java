package org.zerock.todo.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.todo.dto.PageRequestDTO;
import org.zerock.todo.dto.ReplyDTO;

public interface ReplyMapper {
    
    /* gno가 0일 때 실행하는 영역 */
    // 댓글
    int insert(ReplyDTO replyDTO);

    // 댓글의 gno 업데이트
    int updateGno(Long rno);
    /* //gno가 0일 때 실행하는 영역 */

    /* gno가 0이 아닐 때 실행하는 영역 */
    // 대댓글
    int insertChild(ReplyDTO replyDTO);
    /* //gno가 0이 아닐 때 실행하는 영역 */

    // 댓글 list 출력
    List<ReplyDTO> selectList(
        @Param("tno") Long tno,
        @Param("pr") PageRequestDTO pageRequestDTO);

    ReplyDTO selectOne(Long rno);

    @Select("select count(rno) from tbl_reply2 where tno = #{tno}")
    int getTnoCount(Long tno);

}
