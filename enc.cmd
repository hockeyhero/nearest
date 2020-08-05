:: to encrypt: enc + password
openssl-1.1.1\bin\openssl enc -aes-256-ecb  -md md5 -in .\src\main\resources\config\application.properties -out .\src\main\resources\config\application.properties.secrets -k %1
