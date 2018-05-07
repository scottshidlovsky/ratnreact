import React, { Component } from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import {connect} from "react-redux";

const navbarPadding = '15px 20px 12px 20px';
const navbarFocusBorder = '3px solid #ab1327';
const navbarColor = '#171717';

const Navbar = styled.nav`
  display: flex;
  justify-content: space-between;
  box-shadow: 0 1px 6px 0 rgba(0, 0, 0, 0.2);
  margin-bottom: 10px;
`;

const NavTitle = styled(Link)`
  font-size: 1.2rem;
  line-height: 1.5rem;
  font-weight: 600;
  padding: ${navbarPadding};
  color: ${navbarColor};
  display: block;
  text-decoration: none;
  border-bottom: 3px solid transparent;
  &:hover {
    border-bottom: ${navbarFocusBorder};
  }
`;

const NavItem = styled(Link)`
  line-height: 1.5rem;
  font-weight: 400;
  display: block;
  text-decoration: none;
  padding: ${navbarPadding};
  color: ${navbarColor};
  border-bottom: 3px solid transparent;
  &:hover {
    border-bottom: ${navbarFocusBorder};
  }
`;

class Nav extends Component {
  render() {
    return (
      <Navbar>
        <div>
          <NavTitle to="/">Awesome Todo App</NavTitle>
        </div>
        <div>
          {
            this.props.authenticated ? <NavItem to="/">{this.props.username}</NavItem> : <NavItem to="/login">Login</NavItem>
          }
        </div>
      </Navbar>
    )
  }
}

const mapStateToProps = (state) => {
  return {
    authenticated: state.authentication.authenticated,
    username: state.authentication.username
  }
};

export default connect(mapStateToProps)(Nav);