:: to decrypt: dec + password
openssl-1.1.1\bin\openssl enc -d -aes-256-ecb  -md md5 -in .\src\main\resources\application.properties.secrets -out .\src\main\resources\application.properties -k %1