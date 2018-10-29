function login (){
 $('#encryptedPassword').val(hex_sha256($('#encryptedPassword').val()));
 $('#loginForm').submit(); 
}