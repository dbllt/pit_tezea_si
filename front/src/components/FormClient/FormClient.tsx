import React, { Component } from 'react';
import {Container, TextField} from "@material-ui/core";
import Grid from "@material-ui/core/Grid";
import MenuItem from '@material-ui/core/MenuItem';
import Select from '@material-ui/core/Select';
import PhoneInput from 'react-phone-input-2'
import 'react-phone-input-2/lib/style.css'



class FormClient extends Component {
  
  render(){
    return (
        <Container>
          <Grid
            container
            direction="column"
            justify="space-between"
            alignItems="flex-start"
            alignContent="space-between"
           >
              <Grid item className="Grid2">
                <h3>Informations client :</h3>
              </Grid>

              <Grid container alignItems="center" spacing={4}>
                <Grid item>
                  Statut client :        
                </Grid>
                <Grid item> 
                    <Select required>
                      <MenuItem value="P">Particulier</MenuItem>
                      <MenuItem value="E">Entreprise</MenuItem>
                      <MenuItem value="C">Collectivité</MenuItem>
                      <MenuItem value="A">Association</MenuItem>
                    </Select>
                </Grid>            
              </Grid>

              <Grid container alignItems="center" spacing={9}>
                <Grid item>
                  Civilité :        
                </Grid>
                <Grid item> 
                    <Select required>
                      <MenuItem value="M">Monsieur</MenuItem>
                      <MenuItem value="F">Madame</MenuItem>
                    </Select>
                </Grid>            
              </Grid>

              <Grid container alignItems="center" spacing={10}>
                <Grid item>
                  Nom :                    
                </Grid>
                <Grid item>
                  <TextField id="standard-basic" placeholder="Nom" required />
                </Grid>
                <Grid item>
                  Prénom :                    
                </Grid>
                <Grid item>
                  <TextField id="standard-basic" placeholder="Prénom" required />
                </Grid>
              </Grid>

              <Grid container alignItems="center" spacing={4}>
                <Grid item>
                  Téléphone :                    
                </Grid>
                <Grid item>
                    <PhoneInput country={'fr'}/>
                </Grid>
                <Grid item>
                  Email :                    
                </Grid>
                <Grid item>
                  <TextField id="standard-basic" placeholder="Email" required />
                </Grid>
              </Grid>

              <Grid container alignItems="center" spacing={6}>
                <Grid item>
                  Adresse :                    
                </Grid>
                <Grid item>
                  <TextField id="standard-basic" placeholder="Adresse" required />
                </Grid>
                <Grid item>
                  Code postale :                   
                </Grid>
                <Grid item>
                  <TextField id="standard-basic" placeholder="Code postale" required />
                </Grid>
              </Grid>

              <Grid container alignItems="center" spacing={9}>
              <Grid item>
                  Ville :
                </Grid>
                <Grid item>
                  <TextField id="standard-basic" placeholder="Ville" required />
                </Grid>
              </Grid>

          </Grid>
            
        </Container>
    );
  }

}

export default FormClient
