# MCP Server

The Eris service comes with an MCP Server that allows plugging the service
to an LLM agent like Claude or ChatGPT.

The MCP Server is a stateful streamable HTTP.

To interact with the MCP the best approach is to use the [Inspector](https://github.com/modelcontextprotocol/inspector) tool.
It can be installed and run via `npx`:

```bash
$ npx @modelcontextprotocol/inspector
```

To only test whether the server is running without any tool, run the below
CURL command:

```bash
$ curl -i -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: text/event-stream, application/json" \
  -d '{
    "jsonrpc":"2.0",
    "id":1,
    "method":"initialize",
    "params":{
      "protocolVersion":"2025-03-26",
      "capabilities":{},
      "clientInfo":{
        "name":"curl",
        "version":"1.0"
      }
    }
}'
```

You should get a response like this:

```json lines
HTTP/1.1 200
Mcp-Session-Id: 5f207d27-8a30-4fc8-9b61-c9b4c33ad604
X-Content-Type-Options: nosniff
X-XSS-Protection: 0
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 07 May 2026 20:21:41 GMT

{"jsonrpc":"2.0","id":1,"result":{"protocolVersion":"2025-03-26","capabilities":{"completions":{},"logging":{},"prompts":{"listChanged":true},"resources":{"subscribe":false,"listChanged":true},"tools":{"listChanged":true}},"serverInfo":{"name":"eris-weather-mcp-server","version":"v1.0"}}}
```

Copy the `Mcp-Session-Id` and call the `/mcp` endpoint with
the Session-Id as follows:

```bash
$ curl -X POST http://localhost:8080/mcp \
  -H "Content-Type: application/json" \
  -H "Accept: text/event-stream, application/json" \
  -H "Mcp-Session-Id: [SESSION_ID]" \
  -d '{
    "jsonrpc":"2.0",
    "id":2,
    "method":"tools/list",
    "params":{}
}'
```

Then you should get the server details like below:

```json lines
id:5f207d27-8a30-4fc8-9b61-c9b4c33ad604
event:message
data:{"jsonrpc":"2.0","id":2,"result":{"tools":[{"name":"get_current_weather_by_ip_address","title":"get_current_weather_by_ip_address","description":"Get the current weather condition by IP address","inputSchema":{"type":"object","properties":{"ipAddress":{"type":"string","description":"IP Address of a user to translate to geolocation and then calculate weather of a location"},"displayInFahrenheit":{"type":"boolean","description":"Unit of measurement of weather. True means Imperial (Fahrenheit), False means Metric (Celsius). Defaults to Metric when omitted."}},"required":["ipAddress"]},"annotations":{"title":"","readOnlyHint":false,"destructiveHint":true,"idempotentHint":false,"openWorldHint":true}},{"name":"get_current_weather_by_latitude_and_longitude","title":"get_current_weather_by_latitude_and_longitude","description":"Get the current weather condition by latitude and longitude","inputSchema":{"type":"object","properties":{"latitude":{"type":"string","description":"Latitude of a location in string format"},"longitude":{"type":"string","description":"Longitude of a location in string format"},"displayInFahrenheit":{"type":"boolean","description":"Unit of measurement of weather. True means Imperial (Fahrenheit), False means Metric (Celsius). Defaults to Metric when omitted."}},"required":["latitude","longitude"]},"annotations":{"title":"","readOnlyHint":false,"destructiveHint":true,"idempotentHint":false,"openWorldHint":true}}]}}
```