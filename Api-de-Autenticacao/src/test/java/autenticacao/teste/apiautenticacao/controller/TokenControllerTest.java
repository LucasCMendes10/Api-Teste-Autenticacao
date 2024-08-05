//package autenticacao.teste.apiautenticacao.controller;
//
//import autenticacao.teste.apiautenticacao.controller.configuration.TestConfiguration;
//import autenticacao.teste.apiautenticacao.dto.LoginRequestDto;
//import autenticacao.teste.apiautenticacao.handler.ExceptionHandlerAdvice;
//import autenticacao.teste.apiautenticacao.model.User;
//import autenticacao.teste.apiautenticacao.service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = TokenController.class)
//@ContextConfiguration(classes = {TokenController.class, ExceptionHandlerAdvice.class, TestConfiguration.class})
//public class TokenControllerTest {
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private JwtEncoder jwtEncoder;
//
//    @MockBean
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @MockBean
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @DisplayName("login deve retornar 200 caso o login seja realizado com sucesso")
//    @Test
//    void testLoginWithContentAndUserAuthenticated() throws Exception {
//
//        var user = User.builder()
//                .username("admin")
//                .password("123")
//                .build();
//
//        var json = objectMapper.writeValueAsString(user);
//        var dto = objectMapper.readValue(json, LoginRequestDto.class);
//        var jwt = mock(Jwt.class);
//
//        when(user.isLoginCorrect(dto, bCryptPasswordEncoder)).thenReturn(true);
//        when(userService.findByUsername(any())).thenReturn(Optional.of(user));
//        when(jwtEncoder.encode(any())).thenReturn(jwt);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/login")
//                .contentType("application/json")
//                .with(csrf())
//                .content(objectMapper.writeValueAsBytes(dto)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.jwtValue").isNotEmpty())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.expiresIn").isNotEmpty());
//    }
//}
