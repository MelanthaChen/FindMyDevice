# FindMyDevice
**Unifying device tracking with a seamless and centralized web-application.**

## About
<Insert gif here

In the modern age, where our entire lives are encapsulated within our smart devices, safeguarding against theft is more crucial than ever.
While solutions like Apple's 'Find My iPhone' and Android's 'Find My Device' serve their respective ecosystems, our DeviceDetective aspires to revolutionize device tracking by making it seamlessly universal.

### Tech Stack
<details>
  <summary>Server</summary>
  <ul>
    <li><a href="https://spring.io/projects/spring-boot">Spring Boot</a></li>
    <li><a href="https://stomp.github.io/">Stomp</a></li>
    <li><a href="https://cloud.google.com/">Google Cloud Platform</a></li>
    <li><a href="https://www.mongodb.com/">MongoDB</a></li>
  </ul>
</details>

<details>
  <summary>Client</summary>
  <ul>
    <li><a href="https://www.typescriptlang.org/">Typescript</a></li>
    <li><a href="https://nextjs.org/">Next.js</a></li>
    <li><a href="https://reactjs.org/">React.js</a></li>
    <li><a href="https://github.com/sockjs/sockjs-client">SockJS</a></li>
  </ul>
</details>


## Getting Started
### Environmental Variables
To run this project, you will need to edit two `.env` files. In `client/src/.env`, add:  
`NEXT_PUBLIC_GOOGLE_MAPS_API_KEY`

In `server/src/main/.env`, add:  
`MONGODB_URI`

The server will run on port 8080 and the client code will run on port 3000 by default. To start the system, run the server first.
### Server
For the server code, you will need either need to use an IDE that supports Spring Boot (recommended) or install the Spring Boot CLI.

For Mac users, run:
```bash
brew tap spring-io/tap
brew install spring-boot
```

### Client
For the server code, you will need `npm` version 10 or above.

After installing `npm`, navigate to the `client` directory and run:
```bash
npm install
npm run dev
```

## License
See LICENSE.


a text file with a brief outline of the steps needed to execute the application
