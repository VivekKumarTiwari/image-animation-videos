Image Animation Videos
This project is a Spring Boot application built with Maven. It uses Spring Boot  to generate animations/videos from images.

📋 Prerequisites
1. Java 17 installed and configured (java -version should confirm).
2. Maven 3.8+ installed (mvn -v should confirm).
3. An IDE (e.g., IntelliJ IDEA, Eclipse, or VS Code) or command line.
4. Internet connection (to download dependencies).

⚙️ Build Instructions
1. Clone the repository:
   bash 
   git clone https://github.com/VivekKumarTiwari/image-animation-videos.git
   cd image-animation-videos

2. Verify Maven and Java versions:
   java -version
   mvn -v

3. Build the project:
   mvn clean install
   => This will: Download dependencies.
   => Compile the source code.
   => Run tests.
   => Package the application into a JAR file.

▶️ Run Instructions
1. Run the Spring Boot application directly with Maven:
   mvn spring-boot:run
   => OR run the packaged JAR:
   java -jar target/image-animation-videos-0.0.1-SNAPSHOT.jar
2. The application will start on the default port 9080.
   Open your browser and visit:
   http://localhost:9080

📂 API Collections
1. The project includes an api-collections/ directory with ready‑to‑use API request examples.
   api-collections/
   ├── image-animation-videos.postman_collection.json
   └─
2. 🔹 Postman Collection
   Import image-animation-videos.postman_collection.json into Postman.
   Contains endpoints for:
     POST api/video/generate → Generate animation from image



