# Comments

## GET /v1/comments
Fetch all the Comments

```language-curl
curl "http://example.com/v1/comments"
```

> The above command returns JSON structured like this:

```language-javascript
[
   {
      "id": 1,
      "uuid": "42963c97-1d4f-458c-be47-4b96f4133d55",
      "name": "User Name",
      "text": "Comment to the riddle",
      "riddle": {
         "id": 1,
         "uuid": "4d6ef5f0-11c4-45a9-8516-7c22e9dce24c",
         "title": "Riddle title",
         "text": "Text of the riddle",
         "answer": "The answer",
         "rating": 0,
         "category": {
            "id": 2,
            "uuid": "786b6531-34e3-45b4-bcf4-41c24c18f757",
            "title": "Logic"
         }
      }
   },
   ...
]
```

### Response Status Code
`200`


## GET /v1/comments/riddle/:uuid
Fetch all the Comments of the specific riddle

```language-curl
curl "http://example.com/v1/comments/riddle/4d6ef5f0-11c4-45a9-8516-7c22e9dce24c"
```

> The above command returns JSON structured like this:

```language-javascript
[
   {
      "id": 1,
      "uuid": "42963c97-1d4f-458c-be47-4b96f4133d55",
      "name": "User Name",
      "text": "Comment to the riddle",
      "riddle": {
         "id": 1,
         "uuid": "4d6ef5f0-11c4-45a9-8516-7c22e9dce24c",
         "title": "Riddle title",
         "text": "Text of the riddle",
         "answer": "The answer",
         "rating": 0,
         "category": {
            "id": 2,
            "uuid": "786b6531-34e3-45b4-bcf4-41c24c18f757",
            "title": "Logic"
         }
      }
   },
   ...
]
```

### Response Status Code
`200`


## GET /v1/comments/:uuid
Fetch a specific Comment

```language-curl
curl "http://example.com/v1/comments/42963c97-1d4f-458c-be47-4b96f4133d55"
```

> The above command returns JSON structured like this:

```language-javascript
{
  "id": 1,
  "uuid": "42963c97-1d4f-458c-be47-4b96f4133d55",
  "name": "User Name",
  "text": "Comment to the riddle",
  "riddle": {
     "id": 1,
     "uuid": "4d6ef5f0-11c4-45a9-8516-7c22e9dce24c",
     "title": "Riddle title",
     "text": "Text of the riddle",
     "answer": "The answer",
     "rating": 0,
     "category": {
        "id": 2,
        "uuid": "786b6531-34e3-45b4-bcf4-41c24c18f757",
        "title": "Logic"
     }
  }
}
```

### Request Parameters
<table class="attributes">
  <tr>
    <td>uuid<div>UUID v4 String, <span class="req">Required</span></div></td>
    <td>UUID of the Comment to fetch.</td>
  </tr>
</table>

### Response Status Code
`200`



## POST /v1/comments
Create a new Comment.

```language-curl
curl "http://example.com/v1/comments" \
  -d name="John Smith" \
  -d text="New Comment Text" \
  -d riddle_uuid="4d6ef5f0-11c4-45a9-8516-7c22e9dce24c" \
  -X POST
```

> The above command returns JSON structured like this:

```language-javascript
{
  "id": 1,
  "uuid": "42963c97-1d4f-458c-be47-4b96f4133d55",
  "name": "User Name",
  "text": "Comment to the riddle",
  "riddle": {
     "id": 1,
     "uuid": "4d6ef5f0-11c4-45a9-8516-7c22e9dce24c",
     "title": "Riddle title",
     "text": "Text of the riddle",
     "answer": "The answer",
     "rating": 0,
     "category": {
        "id": 2,
        "uuid": "786b6531-34e3-45b4-bcf4-41c24c18f757",
        "title": "Logic"
     }
  }
}
```


### Request Parameters
<table class="attributes">
  <tr>
    <td>name<div>String, <span class="req">Required</span></div></td>
    <td>
      The name of an author of the comment.
    </td>
  </tr>
  <tr>
    <td>text<div>String, <span class="req">Required</span></div></td>
    <td>
      The text of the comment.
    </td>
  </tr>
  <tr>
    <td>riddle_uuid<div>String, <span class="req">Required</span></div></td>
    <td>
      uuid of the riddle to which comment belong
    </td>
  </tr>
</table>

### Response Status Code
`201`



## PUT /v1/comments/:uuid
Update Comment with the given uuid.

```language-curl
curl "http://example.com/v1/comments/42963c97-1d4f-458c-be47-4b96f4133d55" \
  -d name="Updated Name" \
  -d text="Updated Comment Text" \
  -X PUT
```

> The above command returns JSON structured like this:

```language-javascript
{
  "id": 1,
  "uuid": "42963c97-1d4f-458c-be47-4b96f4133d55",
  "name": "Updated Name",
  "text": "Updated Comment Text",
  "riddle": {
     "id": 1,
     "uuid": "4d6ef5f0-11c4-45a9-8516-7c22e9dce24c",
     "title": "Riddle title",
     "text": "Text of the riddle",
     "answer": "The answer",
     "rating": 0,
     "category": {
        "id": 2,
        "uuid": "786b6531-34e3-45b4-bcf4-41c24c18f757",
        "title": "Logic"
     }
  }
}
```

### Request Parameters
<table class="attributes">
  <tr>
    <td>name<div>String, <span class="req">Required</span></div></td>
    <td>
      The name for the comment.
    </td>
  </tr>
  <tr>
    <td>text<div>String, <span class="req">Required</span></div></td>
    <td>
      The text of the comment.
    </td>
  </tr>
</table>

### Response Status Code
 * `200`


## DELETE /v1/comments/:uuid
Delete a specific Comment.

```language-curl
curl "http://example.com/v1/comments/4d7252e7-75c6-470a-b376-cf8f6b99f1f0" \
  -u user:password \
  -X DELETE
```

### Request Parameters
<table class="attributes">
  <tr>
    <td>uuid<div>UUID v4 String, <span class="req">Required</span></div></td>
    <td>UUID of the Comment to delete.</td>
  </tr>
</table>

### Response Status Code
`200`
