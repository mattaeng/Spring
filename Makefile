.PHONY: up
up:
	docker-compose up -d

.PHONY: down
down:
	docker-compose down --rmi all

.PHONY: start
start:
	docker-compose start

.PHONY: stop
clean:
	docker-comopse stop

.PHONY: restart
restart:
	docker-compose restart

.PHONY: rm
rm:
	docker-compose rm

.PHONY: ls
ls:
	docker-compose ls -a

.PHONY: images
images:
	docker-compose images

.PHONY: logs
logs:
	docker-compose logs

.PHONY: stats
stats:
	docker stats
