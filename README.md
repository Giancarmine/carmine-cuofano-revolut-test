<a name="readme-top"></a>

# carmine-cuofano-revolut-test

DevOps Carmine Cuofano Engineer Test


---

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#documentation">Documentation</a>
      <ul>
        <li><a href="#built-with"></a></li>
      </ul>
    </li>
    <li>
      <a href="#try-it">Try it</a>
      <ul>
        <li>Where?</li>
        <li>How?</li>
      </ul>
    </li>
    <li><a href="#tested-on">Tested on</a></li>
    <li><a href="#license">License</a></li>
  </ol>
</details>

---

## About The Project:

This is a simple hello-world project that exposes the following

HTTP-based APIs:

Description:
Saves/updates the given user’s name and date of birth in the database.

Request:

``` bash
PUT /hello/<username> { “dateOfBirth”: “YYYY-MM-DD” }
```

Response:

``` bash
204 No Content
```

Note:

*username must contain only letters.*

*YYYY-MM-DD must be a date before the today date.*

Description:

Returns hello birthday message for the given user

Request:

``` bash
Get /hello/<username>
```

Response:

``` bash
200 OK
```

Response Examples:

- If username’s birthday is in N days:

   ``` bash
   {
       “message”: “Hello, <username>! Your birthday is in N day(s)”
   }
   ```

- If username’s birthday is today:

   ``` bash
   {
      “message”: “Hello, <username>! Happy birthday!”
   }
   ```

### Built With

* [![ktor][ktor]][ktor-url]
* [![gradle][gradle]][gradle-url]
* [![postgres][postgres]][postgres-url]

## Try it:

- Where?

    - Online demo - You can find an online demo deployed on [render.com](https://render.com/) in a docker container
    - Running it locally running by Docker
        - Build image
          ``` bash
          docker build -t carmine-cuofano-revolut-test .
          ```
        - Run image
          ``` bash
          docker run -d carmine-cuofano-revolut-test -e SECRET_KEY_BASE=<SECRET_DB_PASSWORD>
          ```
    - Running it locally running by Java
        - Add DB password to env
          ``` bash
          export SECRET_KEY_BASE=<SECRET_DB_PASSWORD>
          ```
        - Build image
          ``` bash
          java -jar ./carmine-cuofano-revolut-test-all.jar
          ```

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

<!-- Tested on -->

### Tested on:

- Ubuntu 22.04
- Openjdk 17.0.5
- Docker version 20.10.12

---

<!-- License -->

### License

[MIT](https://choosealicense.com/licenses/mit/)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[ktor]: https://img.shields.io/badge/Ktor-v.2.1.2-blue

[ktor-url]: https://ktor.io/

[gradle]: https://img.shields.io/badge/Gradle-v.7.5.1-green

[gradle-url]: https://gradle.org/

[postgres]: https://img.shields.io/badge/PostgreSQL-v.14-9cf

[postgres-url]: https://www.postgresql.org/about/news/postgresql-14-released-2318/