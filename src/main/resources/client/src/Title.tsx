import React from "react";
import { Center, Heading } from "@chakra-ui/react";

const Title = (): JSX.Element => {
  return (
    <Center>
      <div id="topbar">
        <Heading style={{fontFamily: "BadlyStamped"}} size="3xl">CODEZ</Heading>
      </div>
    </Center>
  );
};

export default Title;
