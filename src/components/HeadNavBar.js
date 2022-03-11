import * as React from 'react';
import styled from "styled-components";

export default function HeadNavBar() {
    return (
        <NavbarContainer>
            <MenuItem>Test</MenuItem>
            <MenuItem>Test</MenuItem>
            <MenuItem>Test</MenuItem>
        </NavbarContainer>
    )
}

const MenuItem = styled.a`
  width: 150px;
  background-color: red;
  margin: 10px;
  text-align: center;
  
`

const NavbarContainer = styled.section`
  width: 100%;
  height: 5vh;
  display: flex;
  justify-content: center;
  background-color: ghostwhite;
  align-items: center;
`