This project contains an endpoint (endpoint reference /median) for median calculation.
The request object is defined by a list of rows, where each row contains a list triple of columnames, reacords for cell content and types as an information of the cell content type.
The service filters the lines for each label and calculates the the median für a column. 
The response have the same structure as the request.
The application contains as well a second endpoint to request the median calculation history within the structure of a tuple (date, and processed labels) per median calculation request. 
This endpoint can be reached unter /auditlog. The service can be started as a spring-boot:run application.
The port is set to 8083.
