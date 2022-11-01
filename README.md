<a name="readme-top"></a>

# carmine-cuofano-revolut-test

DevOps Carmine Cuofano Engineer Test


---

## Try it:

- Where?

    - Online demo - You can find an online demo deployed on [render.com](https://render.com/) in a docker container
    - Running it locally running by Docker
    - Running it locally running by Java

- How?

    - Using Postman
        - Download and import env files and requests files
          from [`/postman`](https://github.com/Giancarmine/carmine-cuofano-revolut-test/tree/main/postman) dir

    - Using curl
        - GET hello endpoint
          ``` bash
          curl --location --request GET 'https://carmine-cuofano-revolut-test.onrender.com/hello/carmine'
          ```
        - POST hello endpoint

          ``` bash
          curl --location --request PUT 'https://carmine-cuofano-revolut-test.onrender.com/hello/carmine' \
            --header 'Content-Type: application/json' \
            --data-raw '{
               "dateOfBirth": "1995-05-22"
             }'
          ```

---

### Tested on:

- Ubuntu 22.04
- Openjdk 17.0.5
- Docker version 20.10.12

---

### License

[MIT](https://choosealicense.com/licenses/mit/)

<p align="right">(<a href="#readme-top">back to top</a>)</p>