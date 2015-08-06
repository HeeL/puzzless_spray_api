# Categories

## GET /v1/categories
Fetch all the Categories

```language-curl
curl "http://example.com/v1/categories"
```

> The above command returns JSON structured like this:

```language-javascript
[
   {
      "id": 1,
      "uuid": "65267a33-5434-4b76-a8e7-b19c0340c772",
      "title": "Logic"
   },
   {
      "id": 2,
      "uuid": "122df57d-924a-4f53-ba49-4d17a1a25acb",
      "title": "Math"
   }
]
```

### Response Status Code
`200`


## GET /v1/categories/:uuid
Fetch a specific Category

```language-curl
curl "http://example.com/v1/categories/65267a33-5434-4b76-a8e7-b19c0340c772"
```

> The above command returns JSON structured like this:

```language-javascript
{
  "id": 2,
  "uuid": "65267a33-5434-4b76-a8e7-b19c0340c772",
  "title": "Logic"
}
```

### Request Parameters
<table class="attributes">
  <tr>
    <td>uuid<div>UUID v4 String, <span class="req">Required</span></div></td>
    <td>UUID of the Category to fetch.</td>
  </tr>
</table>

### Response Status Code
`200`



## POST /v1/categories
Create a new Category.

```language-curl
curl "http://example.com/v1/categories" \
  -d title="New Category Title" \
  -X POST
```
> The above command returns JSON structured like this:

```language-javascript
{
  "id": 1,
  "uuid": "83225d3b-9e63-48ae-9c68-cfcbd902529f",
  "title": "New Category Title"
}
```


### Request Parameters
<table class="attributes">
  <tr>
    <td>title<div>String, <span class="req">Required</span></div></td>
    <td>
      The title of the new category.
    </td>
  </tr>
</table>

### Response Status Code
`201`



## PUT /v1/categories/:uuid
Update Category with the given uuid.

```language-curl
curl "http://example.com/v1/categories/83225d3b-9e63-48ae-9c68-cfcbd902529f" \
  -d title="The New Category" \
  -X PUT
```

> The above command returns JSON structured like this:

```language-javascript
{
  "id": 1,
  "uuid": "83225d3b-9e63-48ae-9c68-cfcbd902529f",
  "title": "New Category Title"
}
```

### Request Parameters
<table class="attributes">
  <tr>
    <td>uuid<div>UUID v4 String, <span class="req">Required</span></div></td>
    <td>UUID of the existing Category.</td>
  </tr>
</table>

### Response Status Code
 * `200`


## DELETE /v1/categories/:uuid
Delete a specific Category.

```language-curl
curl "http://example.com/v1/categories/4d7252e7-75c6-470a-b376-cf8f6b99f1f0" \
  -u user:password \
  -X DELETE
```

### Request Parameters
<table class="attributes">
  <tr>
    <td>uuid<div>UUID v4 String, <span class="req">Required</span></div></td>
    <td>UUID of the Category to delete.</td>
  </tr>
</table>

### Response Status Code
`200`
