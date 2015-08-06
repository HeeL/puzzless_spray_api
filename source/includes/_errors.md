# Errors

There are conventional HTTP response codes to indicate success or failure of an API request. Codes in the 2xx range indicate success. Codes in the 4xx range indicate an error that resulted from the provided information. Codes in the 5xx range indicate an error with server. The complete list of http status codes is available on the [wiki page](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes)

### HTTP Status Code Summary
<table class="attributes">
  <tr>
    <td>400</td>
    <td>Bad Request -- You request or parameters are invalid</td>
  </tr>
  <tr>
    <td>401</td>
    <td>Unauthorized -- Your auth credentials are invalid</td>
  </tr>
  <tr>
    <td>403</td>
    <td>Forbidden -- The requested is hidden for authorized users only</td>
  </tr>
  <tr>
    <td>404</td>
    <td>Not Found -- The specified resource could not be found</td>
  </tr>
  <tr>
    <td>405</td>
    <td>Method Not Allowed -- You tried to access a resource with an invalid method</td>
  </tr>
  <tr>
    <td>406</td>
    <td>Not Acceptable -- You requested a format that isn't json</td>
  </tr>
  <tr>
    <td>410</td>
    <td>Gone -- The resource requested has been removed from our servers</td>
  </tr>
  <tr>
    <td>429</td>
    <td>Too Many Requests -- You're requesting too many resources</td>
  </tr>
  <tr>
    <td>500</td>
    <td>Internal Server Error -- We had a problem with our server. Try again later.</td>
  </tr>
  <tr>
    <td>503</td>
    <td>Service Unavailable -- We're temporarially offline for maintanance. Please try again later.</td>
  </tr>
</table>
