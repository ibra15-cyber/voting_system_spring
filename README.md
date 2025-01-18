# Ghana Election Voting System

This is a comprehensive voting system built with Spring Boot to manage and aggregate votes for Ghana's parliamentary and presidential elections. The system includes entities tailored to Ghana's electoral structure, including voters, candidates (both parliamentary and presidential), polling stations, constituencies, districts, regions, and real-time vote aggregation.

## Features

- **Voters Management**: Register and manage voter information.
- **Candidates**: Supports both presidential and parliamentary candidates, including their details and electoral data.
- **Polling Stations**: Management of polling stations, including vote aggregation.
- **Constituencies & Districts**: The system is structured to handle electoral regions, districts, and constituencies.
- **Regions**: Reflects the geographical breakdown of Ghana for proper vote aggregation.
- **Vote Aggregation**: Real-time aggregation of votes for both parliamentary and presidential elections.

## Requirements

- **Java**: 11 or above
- **Spring Boot**: 2.x or above
- **Maven**: 3.x or above
- **Database**: MySQL (or another relational database)
- **JDK**: OpenJDK 11 or above

## Installation

### 1. Clone the repository:

```bash
git clone https://github.com/your-username/ghana-election-voting-system.git
```

### 2. Navigate to the project directory:

```bash
cd ghana-election-voting-system
```

### 3. Configure the database:

- Create a database in MySQL (or another relational database of your choice).
- Update the `application.properties` (or `application.yml`) file in the `src/main/resources` directory with your database connection settings.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ghana_election
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
```

### 4. Build the project:

```bash
./mvnw clean install
```

### 5. Run the application:

```bash
./mvnw spring-boot:run
```

The application should now be running on `http://localhost:8080`.

## Database Structure

The system uses a relational database (MySQL) with the following key tables:

- **Voters**: Stores voter details (e.g., name, ID, constituency).
- **Candidates**: Stores details about presidential and parliamentary candidates (e.g., name, party, position).
- **Polling Stations**: Contains information on polling stations (e.g., location, constituency).
- **Votes**: Stores the votes cast in each polling station.
- **Regions**: Stores region details.
- **Constituencies**: Stores constituency-level data and candidates.
- **Districts**: Stores district-level electoral data.

## API Endpoints

### Voters

- `POST /voters`: Register a new voter.
- `GET /voters/{id}`: Retrieve voter details.
- `GET /voters`: List all voters.

### Candidates

- `POST /candidates`: Add a new candidate (presidential or parliamentary).
- `GET /candidates`: List all candidates.
- `GET /candidates/{id}`: Retrieve candidate details.

### Polling Stations

- `POST /polling-stations`: Create a new polling station.
- `GET  /polling-stations`: List all polling stations.
- `GET /polling-stations/{id}`: Retrieve polling station details.

### Votes

- `POST /votes`: Cast a vote.
- `GET /votes`: List all votes.

### Regions, Districts, and Constituencies

- `GET /regions`: List all regions in Ghana.
- `GET /regions/{id}/constituencies`: List constituencies in a region.
- `GET /districts`: List all districts.
- `GET /constituencies`: List all constituencies.

## Usage

1. **Voter Registration**: Voters can be registered through the system, and their details will be stored for future use.
2. **Casting Votes**: Voters can cast votes for either parliamentary or presidential candidates at designated polling stations.
3. **Vote Aggregation**: Votes will be aggregated based on constituencies, districts, and regions, allowing real-time results for both parliamentary and presidential elections.

## Contributing

Contributions are welcome! Feel free to fork the repository, create a new branch, and submit a pull request with your changes.

### Steps to contribute:
1. Fork the repository.
2. Create a new branch: `git checkout -b feature-name`
3. Make your changes.
4. Commit your changes: `git commit -m 'Add new feature'`
5. Push to your fork: `git push origin feature-name`
6. Open a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for det
