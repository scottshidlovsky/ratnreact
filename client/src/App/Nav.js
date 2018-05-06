import React, { Component } from 'react';
import styled from 'styled-components';

const navbarPadding = '15px 20px 12px 20px';
const navbarFocusBorder = '3px solid #ab1327';
const navbarColor = '#171717';

const Navbar = styled.nav`
  display: flex;
  justify-content: space-between;
  box-shadow: 0 1px 6px 0 rgba(0, 0, 0, 0.2);
`;

const NavTitle = styled.div`
  font-size: 1.2rem;
  line-height: 1.5rem;
  font-weight: 600;
  padding: ${navbarPadding};
  color: ${navbarColor};
  border-bottom: 3px solid transparent;
  &:hover {
    border-bottom: ${navbarFocusBorder};
  }
`;

const NavItem = styled.div`
  line-height: 1.5rem;
  font-weight: 400;
  padding: ${navbarPadding};
  color: ${navbarColor};
  border-bottom: 3px solid transparent;
  &:hover {
    border-bottom: ${navbarFocusBorder};
  }
`;

export default class Nav extends Component {
  render() {
    return (
      <Navbar>
        <div>
          <NavTitle>Awesome Todo App</NavTitle>
        </div>
        <div>
          <NavItem>Login</NavItem>
        </div>
      </Navbar>
    )
  }
}