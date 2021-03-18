import React, { CSSProperties, useState } from "react";
import {
  Box,
  VStack,
  FormControl,
  Input,
  Divider,
  Button,
  HStack,
  Text
} from "@chakra-ui/react";
import { receiveToken} from "../redux/actions";
import Title from "../Title";
import { login, register } from "../apicalls";

const SignupPage = () => {
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  
  const authenticate = async (username, password) => {
    const token = login(username, password)
  }

  return (
    <div
      className="login-grid-root login-background"
    >
      <Title />
      <Divider />
      <div className="font-n1942report">
      <VStack>
        <Text >
          Sign In
        </Text>
        <FormControl>
          <VStack>
        <Input style={{width: '50%'}} size="lg" type="username" placeholder="Username" onChange={(e) => setName(e.currentTarget.value)} />
        <Input style={{width: '50%'}} size="lg" type="password" placeholder="Password" onChange={(e) => setPassword(e.currentTarget.value)} />
        </VStack>
        </FormControl>
        
        <HStack>
        <Button size="lg" 
                className="font-n1942report login-button"
                onClick = {(e) => authenticate(name, password)}
                >LOGIN </Button>
        <Button size="lg" 
                className="font-n1942report signup-button"
                onClick={(e) => register(name,password)}
                >SIGNUP </Button>
        </HStack>
        </VStack>
        <Divider/>
      </div>
    </div>
  );
};

export default SignupPage;