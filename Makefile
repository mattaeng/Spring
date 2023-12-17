green = "\033[32m"
default = "\033[0m"


# docker compose up api # docker-compose.yaml 변경 시
# docker compose up --build api # Dockerfile 변경 시


# 소스 코드 변경 시
.PHONY: builder
builder:
	@echo $(green)Executed: docker compose start builder $(default)
	@docker compose start builder


# 최초 사용 시
.PHONY: up
up:
	@echo $(green)Executed: docker compose up -d builder $(default)
	@docker compose up -d builder
	@echo $(green)Executed: docker compose up -d api mysql $(default)
	@docker compose up -d api mysql


# docker-compose를 통해 생성된 container, network 제거
.PHONY: down
down:
	@echo $(green)Executed: docker compose down $(default)
	@docker compose down


# docker-compose를 통해 생성된 container, network, image 제거
.PHONY: clean
clean:
	@echo $(green)Executed: docker compose down --rmi all $(default)
	@docker compose down --rmi all



# docker-compose를 통해 생성된 container, network, image, 모든 익명 volume 제거
.PHONY: fclean
fclean: clean
	@echo $(green)Executed: docker volume prune -a -f $(default)
	@docker volume prune -f


# 모든 container, image, volume 리스트 출력
.PHONY: ps
ps:
	@echo $(green)Executed: docker compose ps $(default)
	@docker compose ps
	@echo $(green)Executed: docker images $(default)
	@docker images
	@echo $(green)Executed: docker volume ls $(default)
	@docker volume ls


# 입력한 서비스의 로그 출력
.PHONY: logs
logs:
	@docker compose ps
	@printf "[logs] 서비스 이름: "; \
	read service; \
	echo $(green)Executed: docker compose logs $$service $(default); \
	docker compose logs $$service


# 입력한 서비스의 정보 출력
.PHONY: inspect
inspect:
	@docker compose ps
	@printf "[inspect] 서비스 이름: "; \
	read service; \
	echo $(green)Executed: docker inspect $$service $(default); \
	docker inspect $$service; \


# 입력한 서비스로 접속
.PHONY: exec
exec:
	@docker compose ps
	@printf "[exec] 서비스 이름: "; \
	read service; \
	echo $(green)Executed: docker compose exec $$service /bin/bash $(default); \
	docker compose exec $$service /bin/bash; \
