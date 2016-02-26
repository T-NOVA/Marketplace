# Accounting Module User Guide #



* [API Introduction](#rest)

* [API operations](#operations)
	* [Billing module](#Billing)
	* [Service Selection module](#ServiceSelection)
	* [Orchestrator](#Orchestrator)
	* [Dashboard](#Ddashboard)
* [License](#license)

* Available *accounting.sql* file in the root directory of the project.



## <a name="rest">API Introduction</a> ##



The REST interface to the Accounting module has the following conventions:



* Every entity is created with a POST to the collection url. The body request contains the serialized entity in the format specified in the content-type header. The location header of the response refers to the url of the new allocated resource. The return code is a 201 on success. 

* A query for an individual item is a GET to the url of the resource (collection url + external id). The format of the response is specified in the http header with the accept parameter. The return code is 200. As expected, a not found resource returns a 404.

* Any other query is usually a GET to the collection's url, using the GET parameters as the query parameters. The result is a list of entities that match the parameters, despite the actual number of entities. The return code is 200, even if the list is empty.

* Any unexpected error processing the request returns a 5xx.

* An entity (or list) is serialized in the response body by default with the format specified in the Content-type header (if specified). The request may have an Accept header, that will be used if the resource allows more than one Content-type.

* Updating an entity involves a PUT request, with the corresponding resource serialized in the body in the format specified in the content-type header. The return code is 200.

* If a query has begin and/or end parameters, the following search is done: `begin <= entity date < end`
* Fields in an **accounting** entry:
		> - *id*: Number id of the accounting entry.
		> - *instanceId*: Instance number of the service or VNF.
		> - *productId*: Id number of the service or VNF.
		> - *flavour*: Flavour of the product selected by the client.
        > - *agreementId*: Id of the SLA contract of the purchase in the SLA Module.
        > - *relatives*: Id of the service where the VNF belongs to.
        > - *productType*: Could be "service" or "vnf".
        > - *startDate*: Date the service (or VNF) was deployed.
        > - *lastBillDate*: Date the last bill was issued,
        > - *providerId*: Id of the service (or VNF) provider.
        > - *clientId*: Id of the user that purchases the service (or VNF).
        > - *status*: Status of the service (or VNF) [running | stopped | paused].
        > - *billingModel*: Billing model of the transaction.
        > - *period*: Billing period.
        > - *priceUnit*: Currency.
        > - *periodCost*": Cost of the usage of the service (or VNF) per period.
        > - *setupCost* Cost of provisioning and starting the service (or VNF).
        > - *renew*: [true | false] Whether the service is atumatically renewed at the end of the billind period or not (only for subscription model).
        > - *dateCreated*: Date the entry was created.
        > - *dateModified*: Date the entry was modified for the last time.



## <a name="operations">API operations</a> ##


#### <a name="Billing">Billing Module API</a> ####
 
###GET /service-billing-model/?userId={user_id}&instanceId={service_instance_id}###

Details about the user's chosen billing model and specs. for the queried service instance id.

**Parameters:**

* *clientId*: Id of the user we want the billing model details of.
* *instanceId*: String that univocally identifies a service instance.

**Error message:**

* Code 200 with empty results when erroneous data is provided in the call .

**Request**

	GET /service-billing-model/{?clientId=c1&instanceId=id02} HTTP/1.1



**Response in JSON**

	HTTP/1.1 200 OK

	Content-Type: application/json
	
	{...}

**Expected fields in the response**
	<kbd>startDate</kbd>, <kbd>lastBillDate</kbd>, <kbd>billingModel</kbd>, <kbd>period</kbd>,  <kbd>priceUnit</kbd>, <kbd>periodCost</kbd>, <kbd>setupCost</kbd>.
		 


**Example**


	curl -H "Accept: application/json" -X GET http://localhost:8000/service-billing-model/?clientId=c1&instanceId=id02



###GET /service-instance-list/?clientId={client_id}###

List of all active services the user is using.

**Parameters:**

* *clientd*: Id of the user from whom we will get the list of purchased services  (optional)


**Error message*:**

* Code 200 with empty results when erroneous data is provided in the call .

**Request**

	GET /service-instance-list/{?clientId=<client_id>} HTTP/1.1



**Response in JSON**

	HTTP/1.1 200 OK

	Content-Type: application/json
	
	[{...}, {...}, {...}]
	   

**Expected fields in the response**
<kbd>id</kbd>, <kbd>instanceId</kbd>, <kbd>productId</kbd>, <kbd>agreementId</kbd>, <kbd>relatives</kbd>, <kbd>productType</kbd>, <kbd>startDate</kbd>, <kbd>lastBillDate</kbd>, <kbd>providerId</kbd>, <kbd>clientId</kbd>, <kbd>status</kbd>, <kbd>billingModel</kbd>, <kbd>period</kbd>, <kbd>priceUnit</kbd>, <kbd>periodCost</kbd>, <kbd>setupCost</kbd>, <kbd>renew</kbd>, <kbd>dateCreated</kbd>, <kbd>dateModified</kbd>.

**Example**


	curl -H "Accept: application/json" -X GET http://localhost:8000/service-instance-list/?userId=p1



###GET /vnf-list/?clientId={client_id}###

List of all VNFs purchased by a particular provider.

**Parameters:**

* *clientId*: Id of the user for whom we will get all the purchased VNFs.


**Error message*:**

* Code 200 with empty results when erroneous data is provided in the call .

**Request**

	GET /vnf-list/{?clientId=<client_id>} HTTP/1.1



**Response in JSON**

	HTTP/1.1 200 OK

	Content-Type: application/json
	
	[{...}, {...}, {...}]
	   

**Expected fields in the response**
<kbd>id</kbd>, <kbd>instanceId</kbd>, <kbd>productId</kbd>, <kbd>agreementId</kbd>, <kbd>relatives</kbd>, <kbd>productType</kbd>, <kbd>startDate</kbd>, <kbd>lastBillDate</kbd>, <kbd>providerId</kbd>, <kbd>clientId</kbd>, <kbd>status</kbd>, <kbd>billingModel</kbd>, <kbd>period</kbd>, <kbd>priceUnit</kbd>, <kbd>periodCost</kbd>, <kbd>setupCost</kbd>, <kbd>renew</kbd>, <kbd>dateCreated</kbd>, <kbd>dateModified</kbd>.

**Example**


	curl -H "Accept: application/json" -X GET http://localhost:8000/vnf-list/?clientId=p1


###GET /vnf-billing-model/?spId={user_id}&vnfId={VNF_id}###

Details of revenue sharing model between SP and FP for the given VNF.

**Parameters:**

* *spId*: Id service provider that purchased the VNF.
* *vnfId*: Id of the VNF we need the billing model of.

**Error message:**

* Code 200 with empty results when erroneous data is provided in the call .

**Request**

	GET /vnf-billing-model/?spId=<service_provider_id>&vnfId=<vnf_id> HTTP/1.1



**Response in JSON**

	HTTP/1.1 200 OK

	Content-Type: application/json
	
	[{...}, {...}, {...}]

**Expected fields in the response**
	<kbd>startDate</kbd>, <kbd>lastBillDate</kbd>, <kbd>billingModel</kbd>, <kbd>period</kbd>,  <kbd>priceUnit</kbd>, <kbd>periodCost</kbd>, <kbd>setupCost</kbd>.
		 


**Example**


	curl -H "Accept: application/json" -X GET http://localhost:8000/vnf-billing-model/?spId=p1&vnfId=vnf2



###GET /sla/service-violation/?instanceId={service_instance_id}&metric={metric_name}&start={time-date}&end={time-date}###

List of all sla-violations for a given service instance for the queried time window and a give metric name.

**Parameters:**

* *instanceId*: Id of the service instance we want to obtain the SLA violations from.
* *metric*: Metric name, for filtering purposes. (optional)
* *start*: Starting date of the SLA violations time frame. (optional)
* *end*: Ending date of the SLA violations time frame. (optional)

**Error message:**

* Code 200 with empty results when erroneous data is provided in the call .

* Code 500 when there is a connection problem between the Accounting and the SLA modules.

**Request**

	GET /sla/service-violation/?instanceId=<service_instance_id>&metric=<metric_name>&start=<starting_time>&end=<ending_time> HTTP/1.1



**Response in JSON**

	HTTP/1.1 200 OK

	Content-Type: application/json
	
	[{...}, {...}, {...}]

**Response element example**
		
	{
	   "agreementId": "s1vnf2_4",
       "definition": {
	       "expression": "33",
           "type": "discount",
           "validity": "P1D",
           "unit": "%"
       },
       "uuid": "3bd7fc10-0976-46d6-a2c9-a105981bfb18",
       "violation": {
	       "kpiName": "errors_per_hour",
           "actualValue": "90.0",
           "expectedValue": null
       },
       "datetime": "2015-05-26T17:38:30CEST"
	}
		 


**Example**


	curl -H "Accept: application/json" -X GET localhost:8000/sla/service-violation/?instanceId=id02


###GET /sla/vnf-violation/?instanceId={vnf_instance_id}&metric={metric_name}&start={time-date}&end={time-date}###

List of all sla-violations for a given VNF instance for the queried time window and a give metric name.

**Parameters:**

* *instanceId*: Id of the VNF instance we want to obtain the SLA violations from.
* *metric*: Metric name, for filtering purposes. (optional)
* *start*: Starting date of the SLA violations time frame. (optional)
* *end*: Ending date of the SLA violations time frame. (optional)

**Error message:**

* Code 200 with empty results when erroneous data is provided in the call .

**Request**

	GET /sla/vnf-violation/?instanceId=<vnf_instance_id>&metric=<metric_name>&start=<starting_time>&end=<endinf_time> HTTP/1.1



**Response in JSON**

	HTTP/1.1 200 OK

	Content-Type: application/json
	
	[{...}, {...}, {...}]

**Response element example**
		
	{
        "agreementId": "vnf3a2971d0-2eae-11e5-a2cb-0800200c9a66calls5k",
        "definition": {
            "expression": "5",
            "type": "discount",
            "validity": "P1D",
            "unit": "%"
        },
        "uuid": "752472fa-43fd-48d2-8b7f-c857d4422097",
        "violation": {
            "kpiName": "pepito",
            "actualValue": "0.19707928895949645",
            "expectedValue": null
        },
        "datetime": "2015-07-27T09:53:30CEST"
    }
		 


**Example**


	curl -H "Accept: application/json" -X GET localhost:8000/sla/vnf-violation/?instanceId=id15&metric=pepito


###GET /service-list/?vnfId={vnf_id}###

List of all active services that use the given VNF.

**Parameters:**

* *vnfId*: Id of VNF we want to know in how many services it has beed used (optional).


**Error message*:**

* Code 200 with empty results when erroneous data is provided in the call .

**Request**

	GET /service-list/{?vnfId=<vnf_id>} HTTP/1.1



**Response in JSON**

	HTTP/1.1 200 OK

	Content-Type: application/json
	
	[{...}, {...}, {...}]
	   

**Expected fields in the response**
<kbd>id</kbd>, <kbd>instanceId</kbd>, <kbd>productId</kbd>, <kbd>agreementId</kbd>, <kbd>relatives</kbd>, <kbd>productType</kbd>, <kbd>startDate</kbd>, <kbd>lastBillDate</kbd>, <kbd>providerId</kbd>, <kbd>clientId</kbd>, <kbd>status</kbd>, <kbd>billingModel</kbd>, <kbd>period</kbd>, <kbd>priceUnit</kbd>, <kbd>periodCost</kbd>, <kbd>setupCost</kbd>, <kbd>renew</kbd>, <kbd>dateCreated</kbd>, <kbd>dateModified</kbd>.

**Example**


	curl -H "Accept: application/json" -X GET http://localhost:8000/service-list/?vnfId=vnf1



###GET /vnf-instance-list/?sInstanceId={service_instance_id}###

List of all VNf instances that belong to a service determined by its instance id.

**Parameters:**

* *sInstanceId*: Instance id of the service we want obtain the VNF list of.


**Error message*:**

* Code 400 when erroneous data is provided in the call.

**Request**

	GET /vnf-instance-list/{?sInstanceId=<service_instance_id>} HTTP/1.1



**Response in JSON**

	HTTP/1.1 200 OK

	Content-Type: application/json
	
	[{...}, {...}, {...}]
	   

**Expected fields in the response**
<kbd>instanceId</kbd>, <kbd>productId</kbd>.

**Example**


	curl -H "Accept: application/json" -X GET http://localhost:8000/vnf-instance-list/?sInstanceId=id02



#### <a name="ServiceSelection">Service Selection module API</a> ####


###GET /accounts/###

List of all entries in the Accounting system.

**Error message*:**

* Code 200 with empty results when erroneous data is provided in the call .

**Request**

	GET /accounts/ HTTP/1.1


**Response in JSON**

	HTTP/1.1 200 OK

	Content-Type: application/json
	
	[{...}, {...}, {...}]
	   

**Expected fields in the response**
<kbd>id</kbd>, <kbd>instanceId</kbd>, <kbd>productId</kbd>, <kbd>agreementId</kbd>, <kbd>relatives</kbd>, <kbd>productType</kbd>, <kbd>startDate</kbd>, <kbd>lastBillDate</kbd>, <kbd>providerId</kbd>, <kbd>clientId</kbd>, <kbd>status</kbd>, <kbd>billingModel</kbd>, <kbd>period</kbd>, <kbd>priceUnit</kbd>, <kbd>periodCost</kbd>, <kbd>setupCost</kbd>, <kbd>renew</kbd>, <kbd>dateCreated</kbd>, <kbd>dateModified</kbd>.

**Example**


	curl -H "Accept: application/json" -X GET http://localhost:8000/accounts/



###GET /accounts/{accountId}###

Retrieves an accounting entity identified by an accountId.

**Parameters:**

* *accountId*: Id of the accounting entity we want to retrieve (optional).


**Error message*:**

* Code 200 with empty results when erroneous data is provided in the call .
* 404 - Not found: when the provided accountId does not exist in the database.

**Request**

	GET /accounts/{accountId} HTTP/1.1



**Response in JSON**

	HTTP/1.1 200 OK

	Content-Type: application/json
	
	{...}
	   

**Expected fields in the response**
<kbd>id</kbd>, <kbd>instanceId</kbd>, <kbd>productId</kbd>, <kbd>agreementId</kbd>, <kbd>relatives</kbd>, <kbd>productType</kbd>, <kbd>startDate</kbd>, <kbd>lastBillDate</kbd>, <kbd>providerId</kbd>, <kbd>clientId</kbd>, <kbd>status</kbd>, <kbd>billingModel</kbd>, <kbd>period</kbd>, <kbd>priceUnit</kbd>, <kbd>periodCost</kbd>, <kbd>setupCost</kbd>, <kbd>renew</kbd>, <kbd>dateCreated</kbd>, <kbd>dateModified</kbd>.

**Example**


	curl -H "Accept: application/json" -X GET http://localhost:8000/accounts/8/

###POST /accounts/###

Creates a new accounting entry.

**Error message*:**

* 400 - Bad request: when the body in the request is not well formed.
* 408 - Request Timeout: when there is a problem with the status message queue.

**Request**

	POST /accounts/ HTTP/1.1
	Content-type: application/json

	{...}


**Response in JSON**

	HTTP/1.1 201 CREATED
	Content-Type: application/json
	
	{...}
	   

**Required fields in the body**
<kbd>instanceId</kbd>, <kbd>productId</kbd>, <kbd>agreementId</kbd>, <kbd>relatives</kbd>, <kbd>productType</kbd>, <kbd>startDate</kbd>, <kbd>lastBillDate</kbd>, <kbd>providerId</kbd>, <kbd>clientId</kbd>, <kbd>status</kbd>, <kbd>billingModel</kbd>, <kbd>period</kbd>, <kbd>priceUnit</kbd>, <kbd>periodCost</kbd>, <kbd>setupCost</kbd>, <kbd>renew</kbd>.

**Example**


	curl -H "Accept: application/json" -d@<filename> -X POST http://localhost:8000/accounts/


###UPDATE /accounts/{accountId}###

Updates an existing accounting entry. The content in the body will overwrite the content of the resource. The *dateModified* field will be updated with the current time.

**Parameters:**

* *accountId*: Id of the accounting entity we want to update.


**Error message*:**

* 400 - Bad request: when the body in the request is not well formed.
* 404 - Not found: when the provided accountId does not exist in the database.

**Request**

	PUT /accounts/{accountId} HTTP/1.1
	Content-type: application/json

	{...}


**Response in JSON**

	HTTP/1.1 200 OK
	Content-Type: application/json
	
	{...}
	   

**Optional fields in the body**
<kbd>instanceId</kbd>, <kbd>productId</kbd>, <kbd>agreementId</kbd>, <kbd>relatives</kbd>, <kbd>productType</kbd>, <kbd>startDate</kbd>, <kbd>lastBillDate</kbd>, <kbd>providerId</kbd>, <kbd>clientId</kbd>, <kbd>status</kbd>, <kbd>billingModel</kbd>, <kbd>period</kbd>, <kbd>priceUnit</kbd>, <kbd>periodCost</kbd>, <kbd>setupCost</kbd>, <kbd>renew</kbd>.

**Example**


	curl -H "Accept: application/json" -d@<filename> -X PUT http://localhost:8000/accounts/8



###DELETE /accounts/{accountId}###

Deletes an existing accounting entry. 

**Parameters:**

* *accountId*: Id of the accounting entity we want to delete.


**Error message*:**

* 400 - Bad request: when the body in the request is not well formed.
* 404 - Not found: when the provided accountId does not exist in the database.

**Request**

	DELETE /accounts/{accountId}/ HTTP/1.1
	


**Response in JSON**

	HTTP/1.1 204 NO CONTENT
	Content-Type: application/json


**Example**


	curl -H "Accept: application/json" -X DELETE http://localhost:8000/accounts/8/




#### <a name="Orchestrator">Orchestrator API</a> ####


###UPDATE /servicestatus/{ns_instance}/{new_status}/###

Updates the status of a service given its instanceId and the new status. The status of the involved functions will be updated automatically to the new one. 

**Parameters:**

* *ns_instance*: Id service instance we want to change the status.
* *new_status*: name of the status.


**Error message*:**

* Code 200 with empty results when erroneous data is provided in the call .
* 404 - Not found: when the provided accountId does not exist in the database.
* 408 - Request Timeout: when there is a problem with the status message queue.

**Request**

	PUT /servicestatus/<ns_instance_id>/<status>/ HTTP/1.1
	


**Response in JSON**

	HTTP/1.1 200 OK
	Content-Type: application/json
	
	{...}


**Example**


	curl -H "Accept: application/json" -X GET http://localhost:8000/servicestatus/id09/STOP/


#### <a name="Dashboard">Dashboard API</a> ####


###GET /sla-info/?clientId={clientId}&kind={ns|vnf}/###

Retrieve SLA related information to show in the dashboard given the userId and the wheter you want to retrieve VNFs or network services.

**Parameters:**

* *clientId*: Id of the user that is using the network service or the VNF.
* *kind*: It can take 2 values: ns | vnf.


**Error message*:**

* Code 200 with empty results when erroneous data is provided in the call .
* 500 - There is a connection problem between the Accounting and the SLA modules.

**Request**

	GET /sla-info/?clientId=c1&kind=ns HTTP/1.1
	


**Response in JSON**

	HTTP/1.1 200 OK
	Content-Type: application/json
	
	[{...}, {...}, {...}]


**Expected fields in the response**
<kbd>productId</kbd>, <kbd>agreementId</kbd>, <kbd>productType</kbd>, <kbd>dateCreated</kbd>, <kbd>dateTerminated</kbd>, <kbd>providerId</kbd>, <kbd>clientId</kbd>,  <kbd>SLAPenalties</kbd>.

**Example**


	curl -H "Accept: application/json" -X GET http://localhost:8000/sla-info/?clientId=c1&kind=ns


###GET /servicelist/{userId}/###

Retrieves the list of all running services the user (customer) is using. 

**Parameters:**

* *userId*: Id of the user for whom we want to retrieve the service list.


**Error message*:**

* Code 200 with empty results when erroneous data is provided in the call .
* 400 - Bad request: when the body in the request is not well formed.

**Request**

	GET /servicelist/<userId>/ HTTP/1.1
	


**Response in JSON**

	HTTP/1.1 200 OK
	Content-Type: application/json
	
	[{...}, {...}, {...}]


**Expected fields in the response**
<kbd>id</kbd>, <kbd>instanceId</kbd>, <kbd>productId</kbd>, <kbd>agreementId</kbd>, <kbd>relatives</kbd>, <kbd>productType</kbd>, <kbd>flavour</kbd>, <kbd>startDate</kbd>, <kbd>lastBillDate</kbd>, <kbd>providerId</kbd>, <kbd>clientId</kbd>, <kbd>status</kbd>, <kbd>billingModel</kbd>, <kbd>period</kbd>, <kbd>priceUnit</kbd>, <kbd>periodCost</kbd>, <kbd>setupCost</kbd>, <kbd>renew</kbd>, <kbd>dateCreated</kbd>, <kbd>dateModified</kbd>.

**Example**


	curl -H "Accept: application/json" -X GET http://localhost:8000/servicelist/c1/



###GET /vnflist/{userId}/###

Retrieves the list of all running VNFs the user (service provider) is using. 

**Parameters:**

* *userId*: Id of the user for whom we want to retrieve the VNFs list.


**Error message*:**

* Code 200 with empty results when erroneous data is provided in the call .
* 400 - Bad request: when the body in the request is not well formed.

**Request**

	GET /vnflist/<userId>/ HTTP/1.1
	


**Response in JSON**

	HTTP/1.1 200 OK
	Content-Type: application/json
	
	[{...}, {...}, {...}]


**Expected fields in the response**
<kbd>id</kbd>, <kbd>instanceId</kbd>, <kbd>productId</kbd>, <kbd>agreementId</kbd>, <kbd>relatives</kbd>, <kbd>productType</kbd>, <kbd>flavour</kbd>, <kbd>startDate</kbd>, <kbd>lastBillDate</kbd>, <kbd>providerId</kbd>, <kbd>clientId</kbd>, <kbd>status</kbd>, <kbd>billingModel</kbd>, <kbd>period</kbd>, <kbd>priceUnit</kbd>, <kbd>periodCost</kbd>, <kbd>setupCost</kbd>, <kbd>renew</kbd>, <kbd>dateCreated</kbd>, <kbd>dateModified</kbd>.

**Example**


	curl -H "Accept: application/json" -X GET http://localhost:8000/vnflist/p5/






## <a name="license">License</a> ##

Licensed under the [Apache License, Version 2.0][1]

[1]: http://www.apache.org/licenses/LICENSE-2.0
