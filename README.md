# pit_tezea_si

## Authentication

### Development

You can find an example file with http requests

`/test/authentication.rest`



### Production

Set Environment variable **APPLICATION_SECRET** with a secret.

You can generate a secret in a terminal using node :

```
$ node
> require('crypto').randomBytes(64).toString('hex')
```

