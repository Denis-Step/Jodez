import React, { useState, useCallback } from "react";
import {
  InputGroup,
  Button,
  Input,
  InputLeftAddon,
  Stack,
} from "@chakra-ui/react";
import { useDispatch, useSelector } from "react-redux";
import { makeSpymasterMove } from "./redux/actions";

type SpymasterForm = {
  hint: string | null;
  attempts: number | null;
};

const SpymasterBox = (props: {game_ID: string}): JSX.Element => {
  const dispatch = useDispatch();

  const gameState = useSelector((state) => {
    return {
      turn: state.turn,
    };
  });

  const [spyForm, setSpyForm] = useState<SpymasterForm>({
    hint: null,
    attempts: null,
  });

  const handleSubmit = useCallback(() => {
    if (spyForm.hint && spyForm.attempts && spyForm.attempts < 4) {
      dispatch(
        makeSpymasterMove(
          props.game_ID,
          gameState.turn,
          spyForm.hint,
          spyForm.attempts
        )
      );
    }
  }, [spyForm]);

  return (
    <div id="spymasterbox">
      <Stack spacing="3">
        <InputGroup size="sm">
          <InputLeftAddon children="Hint:" />
          <Input
            placeholder="VerySpecificWord"
            onChange={(e) => setSpyForm({ ...spyForm, hint: e.target.value })}
          />
        </InputGroup>

        <InputGroup size="sm">
          <InputLeftAddon children="Attempts:" />
          <Input
            placeholder="From 0 to 3"
            onChange={(e) =>
              setSpyForm({ ...spyForm, attempts: parseInt(e.target.value) })
            }
          />
        </InputGroup>
        <Button
          colorScheme="teal"
          size="lg"
          variant="solid"
          onClick={handleSubmit}
        >
          Submit
        </Button>
      </Stack>
    </div>
  );
};

export default SpymasterBox;
