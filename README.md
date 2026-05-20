# AWS EC2 배포 프로젝트

## 퍼블릭 IP
http://3.35.112.128

## API 엔드포인트

### 회원 관리
- POST /api/members - 회원 생성
- GET /api/members/{id} - 회원 조회

### 프로필 이미지 (레벨3)
- POST /api/members/{id}/profile-image - 프로필 이미지 업로드
- GET /api/members/{id}/profile-image - 프로필 이미지 Presigned URL 조회 (7일 유효)

### 모니터링
- GET /actuator/health - 헬스체크
- GET /actuator/info - 애플리케이션 정보

## 레벨3: S3 프로필 이미지 업로드

### 인프라 구성
- **S3 버킷**: `camp-profile-images-hwangsunnam` (모든 퍼블릭 액세스 차단)
- **IAM Role**: `EC2-S3-Access_Role`
  - AmazonS3FullAccess
  - AmazonSSMReadOnlyAccess
- **Presigned URL**: 7일 유효기간

### 기술 스택
- Spring Cloud AWS 4.0.0 (BOM 패턴)
- AWS SDK S3Client + S3Presigner

### Presigned URL 예시
업로드된 이미지는 7일간 유효한 서명된 URL로 다운로드 가능합니다.

<img width="778" height="657" alt="스크린샷 2026-05-14 142741" src="https://github.com/user-attachments/assets/2602480b-f8c7-4fa5-9f2b-e8bc347ce90b" />
<img width="802" height="574" alt="스크린샷 2026-05-14 142358" src="https://github.com/user-attachments/assets/d855f62c-8700-4e2e-96e5-2ba08f6e6909" />
<img width="728" height="284" alt="image" src="https://github.com/user-attachments/assets/7b889b7a-a786-4456-be11-b8a481e8d1ec" />
<img width="1907" height="848" alt="스크린샷 2026-05-18 160407" src="https://github.com/user-attachments/assets/8bef1960-9b9d-4e8c-8be5-5b4b5064e4b4" />
<img width="1134" height="110" alt="image" src="https://github.com/user-attachments/assets/ec6a5eaf-d21c-4bd9-b2c0-750824c0b1cc" />
<img width="309" height="225" alt="image" src="https://github.com/user-attachments/assets/59d2815e-2dab-4617-994c-9c2881eca3ab" />


<img width="548" height="226" alt="image" src="https://github.com/user-attachments/assets/1d168716-756b-46e1-a58d-26f93477c8c8" />
<img width="1209" height="253" alt="image" src="https://github.com/user-attachments/assets/783e4f22-7a99-4d2b-b994-10670c43c5b0" />


