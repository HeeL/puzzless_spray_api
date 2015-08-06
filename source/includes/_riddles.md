# Riddles

## GET /v1/riddles
Fetch all the Riddles

```language-curl
curl "http://example.com/v1/riddles"
```

> The above command returns JSON structured like this:

```language-javascript
[
   {
      "id": 1,
      "uuid": "65267a33-5434-4b76-a8e7-b19c0340c772",
      "title": "Title of the riddle",
      "text": "Text of the riddle",
      "answer": "Answer of the riddle",
      "rating": 0,
      "category": {
        "id": 2,
        "uuid": "786b6531-34e3-45b4-bcf4-41c24c18f757",
        "title": "Logic"
      }
   },
   ...
]
```

### Response Status Code
`200`


## GET /v1/riddles/category/:uuid
Fetch all the Riddles of the specific category

```language-curl
curl "http://example.com/v1/riddles/category/786b6531-34e3-45b4-bcf4-41c24c18f757"
```

> The above command returns JSON structured like this:

```language-javascript
[
   {
      "id": 1,
      "uuid": "65267a33-5434-4b76-a8e7-b19c0340c772",
      "title": "Title of the riddle",
      "text": "Text of the riddle",
      "answer": "Answer of the riddle",
      "rating": 0,
      "category": {
        "id": 2,
        "uuid": "786b6531-34e3-45b4-bcf4-41c24c18f757",
        "title": "Logic"
      }
   },
   ...
]
```

### Response Status Code
`200`


## GET /v1/riddles/:uuid
Fetch a specific Riddle

```language-curl
curl "http://example.com/v1/riddles/65267a33-5434-4b76-a8e7-b19c0340c772"
```

> The above command returns JSON structured like this:

```language-javascript
{
  "id": 1,
  "uuid": "65267a33-5434-4b76-a8e7-b19c0340c772",
  "title": "Title of the riddle",
  "text": "Text of the riddle",
  "answer": "Answer of the riddle",
  "rating": 0,
  "category": {
    "id": 2,
    "uuid": "786b6531-34e3-45b4-bcf4-41c24c18f757",
    "title": "Logic"
  }
}
```

### Request Parameters
<table class="attributes">
  <tr>
    <td>uuid<div>UUID v4 String, <span class="req">Required</span></div></td>
    <td>UUID of the Riddle to fetch.</td>
  </tr>
</table>

### Response Status Code
`200`



## POST /v1/riddles
Create a new Riddle.

```language-curl
curl "http://example.com/v1/riddles" \
  -d title="New Riddle Title" \
  -d text="New Riddle Text" \
  -d answer="New Riddle Answer" \
  -d category_uuid="786b6531-34e3-45b4-bcf4-41c24c18f757" \
  -X POST
```

> The above command returns JSON structured like this:

```language-javascript
{
  "id": 1,
  "uuid": "65267a33-5434-4b76-a8e7-b19c0340c772",
  "title": "New Riddle Title",
  "text": "New Riddle Text",
  "answer": "New Riddle Answer",
  "rating": 0,
  "category": {
    "id": 2,
    "uuid": "786b6531-34e3-45b4-bcf4-41c24c18f757",
    "title": "Logic"
  }
}
```


### Request Parameters
<table class="attributes">
  <tr>
    <td>title<div>String, <span class="req">Required</span></div></td>
    <td>
      The title for the riddle.
    </td>
  </tr>
  <tr>
    <td>text<div>String, <span class="req">Required</span></div></td>
    <td>
      The text of the riddle.
    </td>
  </tr>
  <tr>
    <td>answer<div>String, <span class="req">Required</span></div></td>
    <td>
      The answer to the riddle.
    </td>
  </tr>
  <tr>
    <td>category_uuid<div>String, <span class="req">Required</span></div></td>
    <td>
      uuid of the category to which riddle belong
    </td>
  </tr>
</table>

### Response Status Code
`201`



## PUT /v1/riddles/:uuid
Update Riddle with the given uuid.

```language-curl
curl "http://example.com/v1/riddles/83225d3b-9e63-48ae-9c68-cfcbd902529f" \
  -d title="Updated Riddle Title" \
  -d text="Updated Riddle Text" \
  -d answer="Updated Riddle Answer" \
  -X PUT
```

> The above command returns JSON structured like this:

```language-javascript
{
  "id": 1,
  "uuid": "65267a33-5434-4b76-a8e7-b19c0340c772",
  "title": "Updated Riddle Title",
  "text": "Updated Riddle Text",
  "answer": "Updated Riddle Answer",
  "rating": 0,
  "category": {
    "id": 2,
    "uuid": "786b6531-34e3-45b4-bcf4-41c24c18f757",
    "title": "Logic"
  }
}
```

### Request Parameters
<table class="attributes">
  <tr>
    <td>title<div>String, <span class="req">Required</span></div></td>
    <td>
      The title for the riddle.
    </td>
  </tr>
  <tr>
    <td>text<div>String, <span class="req">Required</span></div></td>
    <td>
      The text of the riddle.
    </td>
  </tr>
  <tr>
    <td>answer<div>String, <span class="req">Required</span></div></td>
    <td>
      The answer to the riddle.
    </td>
  </tr>
</table>

### Response Status Code
 * `200`


## DELETE /v1/riddles/:uuid
Delete a specific Riddle.

```language-curl
curl "http://example.com/v1/riddles/4d7252e7-75c6-470a-b376-cf8f6b99f1f0" \
  -u user:password \
  -X DELETE
```

### Request Parameters
<table class="attributes">
  <tr>
    <td>uuid<div>UUID v4 String, <span class="req">Required</span></div></td>
    <td>UUID of the Riddle to delete.</td>
  </tr>
</table>

### Response Status Code
`200`
