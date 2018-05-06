import React from 'react';
import styled from 'styled-components';

const StyledInput = styled.input`
  padding: 2px 4px 2px 4px;
  line-height: 1.1rem;
  border: ${props => props.hasError ? '1px solid red' : '1px solid #C5CBD5' };
  border: ;
  outline: none;
  &:focus {
    border: 1px solid #84A2FA;
  }
  
`;

export const Input = (props) => {
  return (
    <StyledInput {...props} />
  )
};

export const Label = styled.label`
  
`;

const FieldInputContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
`;

const FieldLabel = styled.label`
  align-self: flex-start;
  font-size: .9rem;
`;


export const FieldInput = ({label, ...rest}) => {
  return (
    <FieldInputContainer>
      <FieldLabel>{label}</FieldLabel>
      <Input {...rest}></Input>
    </FieldInputContainer>
  )
};

export const FieldError = styled.div`
  color: red;
  font-size: .8rem;
  align-self: flex-start;
`;

export const Button = styled.button`

`;
