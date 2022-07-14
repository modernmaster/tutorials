# ui #

Consumer for configuration service via sidecar

## Building & Running ##

To build run the following command which will build a clean distribution in the `dist` folder

``` sh
webpack
```
or via NPM using
```sh
npm run build
```

For development 

1. Create a `.env` file with the value of API_HOST to point to a host that is serving the API's
   ```sh
   API_HOST=httpbin.org
   ```
2. Update `wbepack.config.js` to update the ports and url prefixes for proxying from the remote API host
   ```json
   {
     //...
     proxy: {
       "/api": {
         changeOrigin: true,
         target: `http://${apiHost}//`,
         pathRewrite: { "^/api": "" },
         agent: proxyAgent
       }
     }
   }
   ```
3. Run the dev server
   ```sh
   webpack-dev-server
   ```
   or 
   ```sh
   npm run start
   ```
4. Visit `http://localhost:4200`
