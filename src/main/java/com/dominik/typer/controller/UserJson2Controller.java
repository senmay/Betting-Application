package com.dominik.typer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user2")
@RequiredArgsConstructor
public class UserJson2Controller {

//    private final UserService userService;
//    private final UserJson2Mapper userJson2Mapper;
//
//    @PostMapping
//    ResponseEntity<UserJson2> createUser(@RequestBody UserJson2 userJson) {
//        System.out.println(userJson);
//        userService.saveUser(userJson2Mapper.mapFromJson(userJson));
//        return ResponseEntity.status(HttpStatus.CREATED).body(userJson);
//    }
//
//    @GetMapping
//    ResponseEntity<List<UserJson2>> getAllUsers()
//    {
//        List<User> userList = userService.getUsers();
//        return ResponseEntity.ok().body(userJson2Mapper.mapToList(userList));
//    }
////    @GetMapping("/pagination")
////    ResponseEntity<Page<UserJson2>> getUsersWithPageSize(Pageable pageable)
////    {
////        Integer pageSize = userPagesize.getPageSize();
////        System.out.println(pageSize);
////        Pageable paging = PageRequest.of(pageable.getPageNumber(), pageSize);
////        Page<User> userPage = userService.getUsersWithPaginating(paging);
////        return ResponseEntity.ok().body(userPage.map(userJson2Mapper::map));
////    }
//
//    @GetMapping("/{id}")
//    ResponseEntity<UserJson2> getUser(@PathVariable Integer id)
//    {
//       User userJson = userService.getUser(id);
//        return ResponseEntity.ok().body(userJson2Mapper.map(userJson));
//    }
//
//    @PutMapping("/{id}")
//    ResponseEntity<UserJson2> updateUser(@PathVariable Integer id, @RequestBody UserJson2 updatedUser)
//    {
//        userService.updateUser(id, userJson2Mapper.mapFromJson(updatedUser));
//        return ResponseEntity.ok().build();
//    }
//    @DeleteMapping("/{id}")
//    ResponseEntity<UserJson2> deleteUser(@PathVariable Integer id)
//    {
//        userService.deleteUser(id);
//        return ResponseEntity.ok().build();
//    }
}
