# 배포스크립트
stop.sh : nginx에 연결되어 있지 않으나, 실행 중인 Spring Boot 종료

start.sh : 새로운 버전의 Spring Boot를 stop.sh로 종료시킨 profile(IDLE_PROFILE)로 실행

health.sh : start.sh로 실행한 Spring Boot의 정상 여부 확인

switch.sh : nginx가 바라보는 Spring Boot를 최신 버전으로 변경 (service nginx reload)

profile.sh : profile과 포트를 체크하는 공용 script
