package org.zerock.todo.mappers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.todo.dto.PageRequestDTO;
import org.zerock.todo.dto.ReplyDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyMapperTest {

    @Autowired(required = false)
    private ReplyMapper replyMapper;

	@Test
	public void testSelectList() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();// 1,10
		Long tno = 100L;

		replyMapper.selectList(tno, pageRequestDTO)
		.forEach(dto -> log.info(dto));
	}

	@Test
    @Transactional
    @Commit
	public void testInsertChild() {
		ReplyDTO replyDTO = ReplyDTO.builder()
        .tno(100L)
        .reply("대댓글 테스트")
        .replyer("replyer00")
		.gno(10L)
        .build();

		replyMapper.insertChild(replyDTO);
	}

    // test에선 기본이 rollback
    @Test
    @Transactional
    @Commit
    public void testInsert() {

        ReplyDTO replyDTO = ReplyDTO.builder()
        .tno(100L)
        .reply("댓글 테스트")
        .replyer("replyer00")
        .build();

        int insertCount = replyMapper.insert(replyDTO);

        Assertions.assertEquals(insertCount, 1);

        if(insertCount != 1){
            throw new RuntimeException("INSERT FAIL");
        }

        Long rno = replyDTO.getRno();
        log.info("RNO: " + rno);

        replyMapper.updateGno(rno);
    }
    
}
