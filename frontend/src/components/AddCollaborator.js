import React, { useState, useRef } from 'react';
import axios from 'axios';
import InputMask from 'react-input-mask';

const AddCollaborator = ({ setCollaborators }) => {
  const [newCollaborator, setNewCollaborator] = useState({
    name: '',
    cpf: '',
  });

  const cpfInputRef = useRef(null);

  const handleInputChange = event => {
    const { name, value } = event.target;
    setNewCollaborator({ ...newCollaborator, [name]: value });
  };

  const redirectToBreakfasts = () => {
    window.location.href = 'http://localhost:3000/breakfasts';
  };

  const addCollaborator = () => {
    axios.post('http://localhost:8080/api/collaborators', newCollaborator)
      .then(response => {
        setNewCollaborator({ name: '', cpf: '' }); 
        cpfInputRef.current.inputElement.value = ''; 
        
        axios.get('http://localhost:8080/api/collaborators')
          .then(response => {
            setCollaborators(response.data);
            redirectToBreakfasts();
          })
          .catch(error => {
            console.error('Error fetching collaborators:', error);
          });
      })
      .catch(error => {
        console.error('Error adding collaborator:', error);
      });
  };

  return (
    <div>
      <h2>Seja um colaborador!</h2>
      <input
        type="text"
        placeholder="Nome"
        name="name"
        value={newCollaborator.name}
        onChange={handleInputChange}
      />
      <InputMask
        mask="999.999.999-99"
        maskChar=""
        type="text"
        placeholder="CPF"
        name="cpf"
        value={newCollaborator.cpf}
        onChange={handleInputChange}
        ref={cpfInputRef}
      />
      <button onClick={addCollaborator}>Add</button>
      <button onClick={redirectToBreakfasts}>Ir para Breakfasts</button>
    </div>
  );
};

export default AddCollaborator;
