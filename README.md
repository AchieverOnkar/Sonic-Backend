
Copy code
# ⚡Sonic 

Sonic-Backend is the Spring Boot-based server-side component/api of the Sonic music streaming service. It provides a robust backend infrastructure for managing users, artists, playlists, favorite songs, and handling payments.

## Features

- **User Service:** Manage user accounts, authentication, and authorization.
- **Artist Service:** Handle artist information and management.
- **Playlist Service:** Create, update, and manage playlists for users.
- **Favorite Song Service:** Allow users to mark and manage their favorite songs.
- **Payment Service:** Securely handle payment transactions for premium features.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) - version 21
- Apache Maven - version ---
- PostgreSQL Database - version 15.0.0

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com:AchieverOnkar/Sonic-Backend.git
   
2. **Configure database settings:**
   ```properties
   spring.datasource.url=jdbc:postgresql://dpg-cn2u7j2cn0vc738jbv80-a/sonic_db
   spring.datasource.username=root
   spring.datasource.password=password
    
3. **Build the project:**
   ```bash
    cd sonic-backend
    mvn clean install
   
4. **Run the application:**
   ```bash
   java -jar target/sonic-backend.jar
   
### Access the API at https://sonic-backend.onrender.com.

### API Documentation
   For detailed API documentation, refer to API.md.

### Services
   User, Artist, Playlist, Favorite Song, and Payment Services
