The application has been written as a recruitment task.

Task description:
Designing a RESTful API to convert units.

Technological limitations:
any language on the JVM platform
any framework
Business requirements:
	the service must be secured with basic-auth
	in order to use the website API, you must first create a user
	user's data is login and password
The API should contain methods for converting units:
	lengths (e.g. meters per mile)
	masses (e.g. kilograms per pound)
	volumes (eg liters per cubic meter)
The last 100 conversions should be stored in the cache (per application)
the user can download the history of their conversions
user conversion history should update with each conversion

Sample data set (the solution API can be completely different):
Input JSON:

input
{
    "inputUnit": "METERS",
    "outputUnit": "MILES",
    "amount": 150.5
}

JSON output:

output
{
    "unit": "MILES",
    "amount": 0.0935163644
}

Non-functional requirements:
The code should be tested as much as possible.
The quality of the code should be as no one would be ashamed of producing
Solution:
The code should be put on GitHub or BitBucket in a public repository.