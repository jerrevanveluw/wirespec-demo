type UserDto {
    userName: String,
    birthDay: String
}

endpoint PostUser POST UserDto /users -> {
    200 -> UserDto
}
