# API de teste

Roda na versão do java 17

OBS! As tabelas serão criadas automaticamente pelo flyway (migration)

## CRUD - Vendedor (Seller)


### listagem com paginação

```
curl --location 'http://localhost:8080/seller'
```

### adicionar

```
curl --location 'http://localhost:8080/seller' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Julio Holanda",
"birthDate": "2024-06-20",
"docNumber": "00527352306",
"email": "bruno@teste.com",
"typeContract": "PJ",
"filialId": 1
}'
```

### editar registro

```
curl --location --request PUT 'http://localhost:8080/seller/5' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Julio Holanda 1",
"birthDate": "2024-06-21",
"docNumber": "00527352306",
"email": "bruno@teste.com",
"typeContract": "PJ",
"filialId": 1
}'
```

### registro por ID

```
curl --location 'http://localhost:8080/seller/5'
```

### deletar por ID

```
curl --location --request DELETE 'http://localhost:8080/seller/4'
```
