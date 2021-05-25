package com.example.demo.src.video;

import com.example.demo.src.user.UserRepository;
import com.example.demo.src.user.model.User;
import com.example.demo.src.video.dto.VideoResDto;
import com.example.demo.src.video.model.Video;
import com.example.demo.src.videoLike.model.VideoLike;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class VideoRepositoryTest {

    @Autowired
    VideoRepository videoRepository;
    @Autowired
    UserRepository userRepository;

//    @Test
//    public void title로page가져오기() {
//
//        PageRequest pageRequest = PageRequest.of(0, 3);
//
//
//        Page<Video> page = videoRepository.findByTitle("title1", pageRequest);
//
//        List<Video> content = page.getContent();
//
//        for (Video video : content) {
//            System.out.println("video : " + video.getTitle());
//        }
//
//    }
/*
    @Test
    public void duration으로_page가져오기() {

        videoRepository.save(new Video("chang3", "des", 3));
        videoRepository.save(new Video("chang4", "des", 4));
        videoRepository.save(new Video("chang5", "des", 5));
        videoRepository.save(new Video("chang6", "des", 6));
        videoRepository.save(new Video("chang7", "des", 7));

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.Direction.DESC, "duration");

        Page<Video> page = videoRepository.findByDuration(8, pageRequest);

        Page<VideoResDto> apiDto = page.map(v -> new VideoResDto(v.getTitle(), v.getDescription(), v.getDuration()));

        List<Video> content = page.getContent();


        for (Video video : content) {
            System.out.println("check : " + video.getTitle());
        }

        for (VideoResDto videoResDto : apiDto) {
            System.out.println("DTO : " + videoResDto.getTitle());
        }

        assertThat(content.size()).isEqualTo(3); // 조회된 데이터수
        assertThat(page.getTotalElements()).isEqualTo(5); // 전체 데이터 수
        assertThat(page.getNumber()).isEqualTo(0); //페이지 번호
        assertThat(page.getTotalPages()).isEqualTo(2); //전체 페이지 번호
        assertThat(page.isFirst()).isTrue(); //첫번째 항목인가?
        assertThat(page.hasNext()).isTrue(); //다음 페이지가 있는가?

    }

*/

//    @Test
//    public void video잘만들어졌는가() {
//
//        User A = userRepository.save(new User("chang"));
//        User B = userRepository.save(new User("chang2"));
//
//
//        videoRepository.save(new Video("title1", "link", A));
//        videoRepository.save(new Video("title2", "link",B));
//        videoRepository.save(new Video("title3", "link",B));
//        videoRepository.save(new Video("title4", "link",A));
//        videoRepository.save(new Video("title5", "link",B));
//
//        PageRequest pageRequest = PageRequest.of(0, 3);
//
//        Page<Video> page = videoRepository.findAll(pageRequest);
//
//        List<Video> content = page.getContent();
//
//        for (Video video : content) {
//            System.out.println("video title : " + video.getTitle());
//
//        }
//
//    }
//
//
//    @Test
//    public void videoLike검사() {
//
//        User user1 = new User("user1");
//        User user2 = new User("user2");
//
//
//        Video video1 = new Video("title1", "des", user1);
//        Video video2 = new Video("title2", "des", user2);
//        Video video3 = new Video("title3", "des", user1);
//        Video video4 = new Video("title4", "des", user2);
//
//
//        VideoLike videoLike1 = new VideoLike(user1, video3);
//        VideoLike videoLike2 = new VideoLike(user2, video2);
//        VideoLike videoLike3 = new VideoLike(user1, video1);
//        VideoLike videoLike4 = new VideoLike(user2, video3);
//        VideoLike videoLike5 = new VideoLike(user2, video3);
//        VideoLike videoLike6 = new VideoLike(user1, video4);
//
//
//
//    }

}