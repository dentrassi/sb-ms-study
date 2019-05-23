# sb-ms-study
Set of Projects to research and understand Spring Boot Micro-services Setup &amp; Approach.

There are 3 services.

1. Reviewer Service - It wraps reqres.in/api/user APIs and present each of these users as Reviewer

2. Movie Catalog Service - It wraps themoviedb.org APIs and presents movie information based on numeric movieid

3. Review Data Service - This contains mock (random) rating and review info for a random combination of reviewer and movie.

All the above services are then used by Movie Review Service that exposes API to get reviews of movies by a given user or get reviews by users of a given movie. 

Eureka Server is used to register each of above service and call them from within Movie Review Service.
