## 요약
이 PR에 대한 설명을 적어주세요.

예시) 유저를 생성하는 기능을 추가하였습니다.

## PR 체크리스트
아래의 요구사항을 충족했다면, 체크 해주세요.
[체크박스 체크 방법](https://www.markdownguide.org/extended-syntax/#task-lists)

- [ ] 작업한 내용에 대해 테스트를 진행했음.
- [ ] untracked files나 unstaged files에 미처 반영하지 않은 코드가 없음. ([`git status`](https://git-scm.com/book/ko/v2/Git%EC%9D%98-%EA%B8%B0%EC%B4%88-%EC%88%98%EC%A0%95%ED%95%98%EA%B3%A0-%EC%A0%80%EC%9E%A5%EC%86%8C%EC%97%90-%EC%A0%80%EC%9E%A5%ED%95%98%EA%B8%B0)로 누락된 코드가 있는지 체크합니다.)

## PR 종류
어떤 종류의 Pull Request 인가요?

- [ ] 기능 추가
- [ ] 버그 수정
- [ ] 코드 스타일 개선
- [ ] 리팩토링 (기능의 변화가 없어야 합니다)
- [ ] 빌드 관련 변경
- [ ] CI 관련 변경
- [ ] 기타 (내용을 적어주세요)

## Breaking change 발생 여뷰
- [ ] 데이터베이스에 존재하는 table 또는 column의 변경/삭제
- [ ] 사용중인 API의 변경 (하위호환이 불가능한) [하위호환이란?](https://namu.wiki/w/%ED%95%98%EC%9C%84%20%ED%98%B8%ED%99%98)
- [ ] 기타 (내용을 적어주세요)

## 어떻게 테스트 할 수 있나요?

> 테스트 관련 스크린샷 또는 영상을 첨부해주시면 리뷰어가 행복하게 리뷰를 진행할 수 있습니다. [PR에 업로드 하는 방법](https://www.seancdavis.com/posts/three-ways-to-add-image-to-github-readme/)
>
> [Mac에서 스크린샷 찍기](https://support.apple.com/ko-kr/HT201361)<br>
> [Mac에서 화면을 기록하는 방법](https://support.apple.com/ko-kr/HT208721)<br>
> [Windows에서 스크린샷 찍기](https://support.microsoft.com/ko-kr/windows/%EC%BA%A1%EC%B2%98-%EB%8F%84%EA%B5%AC%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%98%EC%97%AC-%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7-%EC%BA%A1%EC%B2%98-00246869-1843-655f-f220-97299b865f6b)<br>
> [Windows에서 화면을 기록하는 방법](https://support.xbox.com/ko-KR/help/friends-social-activity/share-socialize/record-game-clips-game-bar-windows-10)

### 예시 1
- case 1: `POST http://localhost:8080/user` 요청시 보낸 데이터에 맞게 유저가 생성되어야 합니다.
- case 2: `POST http://localhost:8080/user` 요청시 이미 존재하는 닉네임을 포함하여 전송하면, 400 Bad Request Error가 발생하면 `이미 존재하는 닉네임 입니다` 라는 응답이 와야 합니다.

### 예시 2
- case 1: `GET http://localhost:8080/user/123` 요청을 했을 때, 200 응답과 함께 id가 123인 User 데이터를 얻을 수 있어야 합니다.
- case 2: `GET http://localhost:8080/user/invalidid` 요청을 했을 때, 404 Not Found Error가 발생합니다.

<img width="700" alt="스크린샷 2022-07-11 오전 1 47 05" src="https://user-images.githubusercontent.com/34048253/178154105-4b90ef01-e724-4e5d-a8ee-b7d384662ba2.png">
