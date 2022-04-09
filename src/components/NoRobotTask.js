import * as React from 'react';
import {useState} from 'react';
import {InputText} from "primereact/inputtext";
import styled from "styled-components/macro";

export default function NoRobotTask({handleRobot}) {
    const [input] = useState({first: Math.floor(Math.random() * 9 + 1), second: Math.floor(Math.random() * 9 + 1)});
    const validInput = (answer) => {
        const result = input.first + input.second;
        if (answer === result) {
            handleRobot(true)
        } else {
            handleRobot(false)
        }
    }

    const handleAnswer = (e) => {
        validInput(parseInt(e.target.value))
    }

    return (
        <StyledRobotTaskContainer>
            <StyledQuestionContainer>
                <p>{input.first}</p>
                <p> + </p>
                <p>{input.second}</p>
            </StyledQuestionContainer>
            <p>=</p>
            <StyledAnswerBox name="answer" onChange={handleAnswer}/>
        </StyledRobotTaskContainer>
    )
}

const StyledAnswerBox = styled(InputText)`
  width: 50px;
  height: 50px;
  margin-left: 50px;
`

const StyledRobotTaskContainer = styled.section`
  display: flex;
  align-items: center;
`

const StyledQuestionContainer = styled.section`
  display: flex;
  justify-content: center;
`