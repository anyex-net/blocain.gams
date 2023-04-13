-- Test grant_type = 'authorization_code' ,  get code

http://localhost:9000/oauth/authorize?response_type=code&scope=read,write&client_id=trade&redirect_uri=http://localhost:8081/oauth/check&state=09876999555

-- Test grant_type = 'token'
-- implicit

http://localhost:9000/oauth/authorize?response_type=token&scope=read,write&client_id=trade&redirect_uri=http://localhost:8080/oauth/authorization_code_callback


-- Test from 'code' get 'token'   [POST]
http://localhost:9000/oauth/token?client_id=trade&client_secret=trade123456&grant_type=authorization_code&code=e311e7340e4b12c13e59fd84eadbb9ba&redirect_uri=http://localhost:8080/oauth/authorize?response_type=code&scope=read,write&state=09876999



-- Test grant_type='password'   [POST]
http://localhost:9000/oauth/token?client_id=trade&client_secret=trade123456&grant_type=password&scope=read write&username=test&password=123456



-- Test grant_type='client_credentials'  [POST]
http://localhost:9000/oauth/token?client_id=trade&client_secret=trade123456&grant_type=client_credentials&scope=read


-- Test grant_type='refresh_token'  [POST]
http://localhost:9000/oauth/token?client_id=trade&client_secret=trade123456&grant_type=refresh_token&refresh_token=b36f4978a1724aa8af8960f58abe3ba1

--注意:  scope是用空格分隔的, 如:read write



-- Test resource
http://localhost:8080/rs/rs/username?access_token=6b7b0726e603cd04c797d5b28c7be4c4


--
-- Mobile Resource
--

-- Test grant_type='password'   [POST]
http://localhost:9000/oauth/token?client_id=trade&client_secret=trade123456&grant_type=password&scope=read,write&username=test&password=123456



-- Test mobile resource
http://qc8.cc:8080/rs/mobile/system_time?access_token=b69c2f61212780c901e69cebd6854667

