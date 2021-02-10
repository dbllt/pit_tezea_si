import React from 'react';
import {Link} from "react-router-dom";
import {createStyles, makeStyles, Theme} from '@material-ui/core/styles';
import {Button, Container, TextField} from "@material-ui/core";
import Grid from "@material-ui/core/Grid";
import Form from "@material-ui/core/FormControl";
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        root: {
            display: 'flex',
            flexWrap: 'wrap',
        },
        textField: {
            marginLeft: 1,
            marginRight: 1,
        },
        formControl: {
            margin: 1,
            minWidth: 120,
        },
        selectEmpty: {
            marginTop: 1,
        },
        h4: {
            margin: 20,
        },
        form: {
            margin: 20,
        }
    }),
);

export default function NewRequest() {
    const classes = useStyles();

    const [timeMode, setTimeMode] = React.useState('');
    const handleChange = (event: React.ChangeEvent<{ value: unknown }>) => {
        setTimeMode(event.target.value as string);
    };

    return (
        <Container>
            <h1>Enregistrer une demande client</h1>
            <Form className={classes.form}>
                <Grid container direction="row" justify="flex-start" alignItems="flex-start" spacing={1}>
                    <h4 className={classes.h4}>Informations client</h4>
                    <Grid container justify={"space-evenly"}>
                        <Grid item>
                            <TextField
                                label="Nom du client:"
                                id="outlined-margin-normal"
                                className={classes.textField}
                                margin="normal"
                                variant="outlined"
                            />
                        </Grid>
                        <Grid item>
                            <TextField
                                label="Prénom du client:"
                                id="outlined-margin-normal"
                                className={classes.textField}
                                margin="normal"
                                variant="outlined"
                            />
                        </Grid>
                    </Grid>
                    <Grid container justify={"space-evenly"}>
                        <Grid item>
                            <TextField
                                label="Téléphone client:"
                                id="outlined-margin-normal"
                                className={classes.textField}
                                margin="normal"
                                variant="outlined"
                            />
                        </Grid>
                        <Grid item>
                            <TextField
                                label="Mail client:"
                                id="outlined-margin-normal"
                                className={classes.textField}
                                margin="normal"
                                variant="outlined"
                            />
                        </Grid>
                    </Grid>
                </Grid>
                <Grid container direction="row" justify="flex-start" alignItems="flex-start" spacing={1}>
                    <h4 className={classes.h4}>Adresse</h4>
                    <Grid container justify={"space-evenly"}>
                        <Grid item>
                            <TextField
                                label="Adressse:"
                                id="outlined-margin-normal"
                                className={classes.textField}
                                margin="normal"
                                variant="outlined"
                            />
                        </Grid>
                        <Grid item>
                            <TextField
                                label="Code Postal:"
                                id="outlined-margin-normal"
                                type="number"
                                className={classes.textField}
                                margin="normal"
                                variant="outlined"
                            />
                        </Grid>
                    </Grid>
                    <Grid container justify={"space-evenly"}>
                        <Grid item>
                            <TextField
                                label="Ville"
                                id="outlined-margin-normal"
                                className={classes.textField}
                                margin="normal"
                                variant="outlined"
                            />
                        </Grid>
                    </Grid>
                </Grid>
                <Grid container direction="row" justify="flex-start" alignItems="flex-start" spacing={1}>
                    <h4 className={classes.h4}>Demande client</h4>
                    <Grid container justify={"space-evenly"}>
                        <Grid item>
                            <TextField
                                id="outlined-multiline-static"
                                multiline
                                fullWidth={true}
                                rows={4}
                                variant="outlined"
                            />
                        </Grid>
                    </Grid>
                </Grid>
                <Grid container direction="row" justify="flex-start" alignItems="flex-start" spacing={1}>
                    <h4 className={classes.h4}>Matériel attendu</h4>
                    <Grid container justify={"space-evenly"}>
                        <Grid item>
                            <TextField
                                id="outlined-margin-normal"
                                className={classes.textField}
                                margin="normal"
                                variant="outlined"
                            />
                        </Grid>
                    </Grid>
                </Grid>
                <Grid container direction="row" justify="flex-start" alignItems="flex-start" spacing={1}>
                    <h4 className={classes.h4}>Estimation du temps</h4>
                    <Grid container
                          alignItems="center"
                          justify="center"
                          spacing={2}>
                        <Grid item>
                            <TextField
                                id="outlined-margin-normal"
                                className={classes.textField}
                                margin="normal"
                                variant="outlined"
                                type="number"
                            />
                        </Grid>
                        <Grid item>
                            <p>jours</p>
                        </Grid>
                        <Grid item>
                            <TextField
                                id="outlined-margin-normal"
                                className={classes.textField}
                                margin="normal"
                                variant="outlined"
                                type="number"
                            />
                        </Grid>
                        <Grid item>
                            <p>heures</p>
                        </Grid>
                        <Grid item>
                            <TextField
                                id="outlined-margin-normal"
                                className={classes.textField}
                                margin="normal"
                                variant="outlined"
                                type="number"
                            />
                        </Grid>
                        <Grid item>
                            <p>minutes</p>
                        </Grid>
                    </Grid>
                </Grid>
                <Grid container direction="row" justify="flex-start" alignItems="flex-start" spacing={1}>
                    <h4 className={classes.h4}>Régularité</h4>
                    <Grid container
                          alignItems="center"
                          justify="center"
                          spacing={2}>
                        <Grid item>
                            <TextField
                                id="outlined-margin-normal"
                                className={classes.textField}
                                margin="normal"
                                variant="outlined"
                                type="number"
                            />
                        </Grid>
                        <Grid item>
                            <p>par</p>
                        </Grid>
                        <Grid item>
                            <FormControl variant="outlined" className={classes.formControl}>
                                <Select
                                    labelId="demo-simple-select-outlined-label"
                                    id="demo-simple-select-outlined"
                                    value={timeMode}
                                    onChange={handleChange}
                                >
                                    <MenuItem value="day">jour</MenuItem>
                                    <MenuItem value="week">semaine</MenuItem>
                                    <MenuItem value="month">mois</MenuItem>
                                    <MenuItem value="year">année</MenuItem>
                                </Select>
                            </FormControl>
                        </Grid>
                    </Grid>
                </Grid>
                <Grid container direction="row" justify="flex-start" alignItems="flex-start" spacing={1}>
                    <h4 className={classes.h4}>Particularité</h4>
                    <Grid container justify={"space-evenly"}>
                        <Grid item>
                            <TextField
                                id="outlined-multiline-static"
                                multiline
                                fullWidth={true}
                                rows={4}
                                variant="outlined"
                            />
                        </Grid>
                    </Grid>
                </Grid>
                <Grid container direction="row" justify="flex-start" alignItems="flex-start" spacing={1}>
                    <h4 className={classes.h4}>Informations internes</h4>
                    <Grid container justify={"space-evenly"}>
                        <Grid item>
                            <TextField
                                id="outlined-multiline-static"
                                multiline
                                fullWidth={true}
                                rows={4}
                                variant="outlined"
                            />
                        </Grid>
                    </Grid>
                </Grid>
            </Form>


            <Button variant="contained" type="submit">
                Enregistrer la demande
            </Button>

            <Link to="/menu">
                <Button type="button">
                    Retour
                </Button>
            </Link>
        </Container>
    );
}
