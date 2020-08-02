:: to encrypt: enc + password
::set path=%path%;%~dp0openssl-1.1.1\bin
openssl-1.1.1\bin\openssl enc -aes-256-ecb -pbkdf2 -md md5 -in .\src\main\resources\application.properties -out .\src\main\resources\application.properties.secrets -k %1
