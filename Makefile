api-catalog-up:
	docker compose up -d

api-catalog-down:
	docker compose down

api-catalog-logs:
	docker compose logs -f