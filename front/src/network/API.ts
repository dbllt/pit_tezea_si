import {Filter} from '../components/BusinessTableFilter/BusinessTableFilter';


interface Request {
    id: string;
    date: string,
    hour: string,
    concierge: string,
    site: string,
    requestStatus: string,
    requestAssignment: string,
    executionDate: Date,
    typeRequest: string,
    requestDesc: string,
    numberPerson: string,
    place: string,
    regularity: string,
    duration: string,
    material: string,
    internalInfo: string,
    images: File [],
    client: IClient
}

interface IClient {
    clientStatus: string,
    company: string,
    gender: string,
    lName: string,
    fName: string,
    phone: string,
    email: string,
    address: string,
    cp: string,
    city: string
}

export interface BackendRequest {
    id:                    number;
    date:                  Date;
    site:                  backendSite;
    responsable:           backendClosedBy;
    client:                backendClient;
    priority:              string;
    description:           string;
    status:                string;
    closedBy:              backendClosedBy;
    accessDetails:         string;
    repetitionTime:        number;
    repetitionUnit:        string;
    type:                  string;
    amountWood:            number;
    amountDonated:         number;
    appointmentPlasmaDate: Date;
    estimation:            backendEstimation;
    satisfactionLevel:     string;
    lastUpdated:           Date;
    lastUpdatedBy:         backendClosedBy;
    photos:                string[];
}

export interface backendClient {
    id:             number;
    email:          string;
    phoneNumber:    string;
    address:        string;
    postCode:       string;
    city:           string;
    companyName:    string;
    lastName:       string;
    firstName:      string;
    honorificTitle: string;
}

export interface backendClosedBy {
    id:       number;
    username: string;
}

export interface backendEstimation {
}

export interface backendSite {
    name: string;
}




interface Client {
    id: string;
    name: string,
    mail: string
}

interface User {
    id: string;
    username: string;
    authorities: string[];
}


const serge: User = {
    id: "0",
    username: "serge",
    authorities: ["serge"]
}

const pierre: User = {
    id: "1",
    username: "pierre",
    authorities: ["concierge"]
}

const paul: Client = {
    id: "0",
    name: "paul",
    mail: "paul@mail.fr"
}

const date1 = new Date(2021, 1, 28);
const date2 = new Date(2021, 2, 5);
const date3 = new Date(2021, 2, 15);

let requests: Request[] = [];
addRequest("1", "2018-01-25", "10:30", "Jouadé", "Menuiserie", "Don", "En cours", "Ouvrier 3", date1, "Particulier", "Google", "M.", "Nom", "Pierre", "353535550", "email@email", "1 rue de la Paix", "35000", "Rennes", "Faire un truc", "3", "?", "2 fois par jour", "1 an", "1 camion", "coucou", [])
addRequest("2", "2018-01-25", "10:30", "test", "Autre", "Don", "En cours", "Ouvrier 3", date2, "Entreprise", "Google", "M.", "Nom", "Paul", "353535550", "email@email", "1 rue de la Paix", "35000", "Rennes", "Faire un truc", "2", "?", "2 fois par jour", "1 an", "1 camion", "coucou", [])
addRequest("3", "2018-01-25", "10:30", "Jouadé", "Menuiserie", "Don", "En cours", "Ouvrier 3", date3, "Particulier", "Amazon", "M.", "Nom", "Jaques", "353535550", "email@email", "1 rue de la Paix", "35000", "Rennes", "Faire un truc", "1", "?", "2 fois par jour", "1 an", "1 camion", "coucou", [])
addRequest("4", "2018-01-25", "10:30", "Jouadé", "Menuiserie", "Don", "En cours", "Ouvrier 3", date1, "Particulier", "Amazon", "M.", "Nom", "Jean-Abdourrahmane", "353535550", "email@email", "1 rue de la Paix", "35000", "Rennes", "Faire un trucCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCcc", "2", "?", "2 fois par jour", "1 an", "1 camion", "coucou", [])
addRequest("5", "2018-01-25", "10:30", "Jouadé", "Autre", "Don", "En cours", "Ouvrier 3", date1, "Entreprise", "Amazon", "M.", "Nom", "Prénom", "353535550", "email@email", "1 rue de la Paix", "35000", "Rennes", "Faire un truc", "3", "?", "2 fois par jour", "1 an", "1 camion", "coucou", [])
addRequest("6", "2018-01-25", "10:30", "test", "Menuiserie", "Enlevement", "En cours", "Ouvrier 3", date3, "Particulier", "Facebook", "M.", "Nom", "Prénom", "353535550", "email@email", "1 rue de la Paix", "35000", "Rennes", "Faire un truc", "1", "?", "2 fois par jour", "1 an", "1 camion", "coucou", [])
addRequest("7", "2018-01-25", "10:30", "Jouadé", "Autre", "Don", "En cours", "Ouvrier 3", date2, "Particulier", "Amazon", "M.", "Nom", "Prénom", "353535550", "email@email", "1 rue de la Paix", "35000", "Rennes", "Faire un truc", "4", "?", "2 fois par jour", "1 an", "1 camion", "coucou", [])
addRequest("8", "2018-01-25", "10:30", "test", "Menuiserie", "Prestation", "En cours", "Ouvrier 3", date3, "Entreprise", "Facebook", "M.", "Nom", "Prénom", "353535550", "email@email", "1 rue de la Paix", "35000", "Rennes", "Faire un truc", "1", "?", "2 fois par jour", "1 an", "1 camion", "coucou", [])
addRequest("9", "2018-01-25", "10:30", "Jouadé", "Menuiserie", "Don", "En cours", "Ouvrier 3", date2, "Particulier", "Facebook", "M.", "Nom", "Prénom", "353535550", "email@email", "1 rue de la Paix", "35000", "Rennes", "Faire un truc", "2", "?", "2 fois par jour", "1 an", "1 camion", "coucou", [])


let clients: Client[] = [];
let users: User[] = [];
users.push(serge);
users.push(pierre);
clients.push(paul);

function addRequest(
    id: string,
    date: string,
    hour: string,
    concierge: string,
    site: string,
    typeRequest: string,
    requestStatus: string,
    requestAssignment: string,
    executionDate: Date,
    clientStatus: string,
    company: string,
    gender: string,
    lName: string,
    fName: string,
    phone: string,
    email: string,
    address: string,
    cp: string,
    city: string,
    requestDesc: string,
    numberPerson: string,
    place: string,
    regularity: string,
    duration: string,
    material: string,
    internalInfo: string,
    images: File [],
) {

    const temp: IClient = {
        clientStatus: clientStatus,
        gender: gender,
        lName: lName,
        fName: fName,
        phone: phone,
        email: email,
        address: address,
        cp: cp,
        city: city,
        company: company,

    }
    const request: Request = {
        concierge: concierge,
        date: date,
        duration: duration,
        executionDate: executionDate,
        hour: hour,
        id: id,
        images: images,
        internalInfo: internalInfo,
        material: material,
        numberPerson: numberPerson,
        place: place,
        regularity: regularity,
        requestAssignment: requestAssignment,
        requestDesc: requestDesc,
        requestStatus: requestStatus,
        site: site,
        typeRequest: typeRequest,
        client: temp
    }

    requests.push(request);

}

const API = {
    login: async function (username: string, password: string): Promise<boolean> {
        let found = false;

        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({username: username, password: password})
        };
        await fetch('/auth/authenticate', requestOptions)
            .then(async response => {
                const data = await response.json();
                if (!response.ok) {
                    const error = (data && data.message) || response.status;
                    found = false;
                    return Promise.reject(error);
                } else {
                    const token = data.token
                    localStorage.setItem('token', token)
                    const refreshToken = data.refreshtoken
                    localStorage.setItem('refreshToken', refreshToken)
                    var role: string = "";
                    const temp = data.authorities[0]
                    if (temp !== undefined) {
                        role = temp.authority;
                    } else {
                        role = ""
                    }
                    localStorage.setItem('role', role)
                    found = true;
                }
            }).catch(error => {
                console.error('There was an error!', error);
            })
        return found;
    },


    disconnect: async function (): Promise<any> {

        let temp = localStorage.getItem('token');
        if (temp === null) {
            temp = "";
        }
        let token: string = temp;

        temp = localStorage.getItem('refreshToken');
        if (temp === null) {
            temp = "";
        }
        let refreshToken: string = temp;
        const requestOptions = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + token
            },
            body: JSON.stringify({refreshToken: refreshToken})
        };

        await fetch('/auth/token', requestOptions)
            .then(async response => {
                if (response.status !== 204) {
                    return Promise.reject(response);
                } else {
                    localStorage.setItem('token', "")
                    localStorage.setItem('refreshToken', "")
                }
            }).catch(error => {
                console.error('There was an error!', error);
            })


    },

    getRole: function (): string {
        var ret = localStorage.getItem('role')
        if (ret === null) {
            ret = ""
        }
        return ret;
    },


    addRequest: async function (request: Request): Promise<any> {


        let temp = localStorage.getItem('token');
        if (temp === null) {
            temp = "";
        }
        let token: string = temp;


        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + token
            },
            body: JSON.stringify({})
        };

        await fetch('/requests/create', requestOptions)
            .then(async response => {
                if (response.status !== 201) {
                    return Promise.reject(response);
                } else {
                    const data:BackendRequest= await response.json();

                    console.log(data.date)
                }
            }).catch(error => {
                console.error('There was an error!', error);
            })


    },
    getRequests: async function (filter: Filter): Promise<Request[]> {

        let temp = localStorage.getItem('token');
        if (temp === null) {
            temp = "";
        }
        let token: string = temp;


        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + token
            },
            body: JSON.stringify({
            })
        };

        await fetch('/requests', requestOptions)
            .then(async response => {
                if (response.status !== 200) {
                    return Promise.reject(response);
                } else {
                    const data = await response.json();
                    console.log(data)
                }
            }).catch(error => {
                console.error('There was an error!', error);
            })


        return requests.filter((request => request.site.toLocaleLowerCase().includes(filter.site.toLocaleLowerCase())))
    },


    uploadFile(file: File) {

        const formData = new FormData();

        formData.append('image', file);

        fetch('/upload', { // Your POST endpoint
            method: 'POST',
            body: formData // This is your file object
        }).then(
            response => console.log(response) // if the response is a JSON object
        ).then(
            success => console.log(success) // Handle the success response object
        ).catch(
            error => console.log(error) // Handle the error response object
        );
    },

    getRequest: async function (id: string): Promise<any> {
        // eslint-disable-next-line
        if(id =="-1")
            return null;

        let temp = localStorage.getItem('token');
        if (temp === null) {
            temp = "";
        }
        let token: string = temp;


        const requestOptions = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + token
            },
        };

        await fetch('/request/' + id, requestOptions)
            .then(async response => {
                if (response.status !== 200) {
                    //return Promise.reject(response);
                } else {
                    //const data = await response.json();
                }
            }).catch(error => {
                console.error('There was an error!', error);
            })


        let ret = null
        requests.forEach(function (request) {
            if (request.id === id) {
                ret = request;
            }
        });
        return ret;
    },


    editRequest: async function (id: string, request: Request): Promise<any> {
        let index = requests.findIndex(x => x.id === id);
        if (index > -1) {
            requests.splice(index, 1);
            requests.push(request);
        }
    },
    removeRequest: async function (id: string): Promise<any> {
        let index = requests.findIndex(x => x.id === id);
        if (index > -1) {
            requests.splice(index, 1);
        }
    },
    getClients: async function (): Promise<Client[]> {
        return clients;
    },
    addClient: async function (name: string, mail: string): Promise<any> {
        const client: Client = {
            id: clients.length.toString(),
            name: name,
            mail: mail
        };

        clients.push(client);

    },

    getClient: async function (id: string): Promise<any> {
        clients.forEach(function (client) {
            if (client.id === id) {
                return client;
            }
        });
        return false;
    },

    editClient: async function (id: string, client: Client): Promise<any> {
        let index = clients.findIndex(x => x.id === id);
        if (index > -1) {
            clients.splice(index, 1);
            clients.push(client);
        }
    },

    removeClient: async function (id: string): Promise<any> {
        let index = clients.findIndex(x => x.id === id);
        if (index > -1) {
            clients.splice(index, 1);
        }
    },


    getUsers: async function (): Promise<User[]> {
        let temp = localStorage.getItem('token');
        if (temp === null) {
            temp = "";
        }
        let token: string = temp;


        const requestOptions = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + token
            },
        };


        let ret: User[] = []
        await fetch('/users', requestOptions)
            .then(async response => {
                if (response.status !== 200) {
                    return Promise.reject(response);
                } else {
                    const data = await response.json();
                    ret = data;
                }
            }).catch(error => {
                console.error('There was an error!', error);
            })
        return ret;
    },

    addUser: async function (username: string, password: string, role: string): Promise<any> {

        let temp = localStorage.getItem('token');
        if (temp === null) {
            temp = "";
        }
        let token: string = temp;
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + token
            },
            body: JSON.stringify({username: username, password: password, authorities: [role]})
        };

        await fetch('/register', requestOptions)
            .then(async response => {
                if (!response.ok) {
                    return Promise.reject(response);
                }
            }).catch(error => {
                console.error('There was an error!', error);
            })


    },

    getUser: async function (id: string): Promise<any> {
        users.forEach(function (user) {
            if (user.id === id) {
                return user;
            }
        });
        return false;
    },

    editUser: async function (id: string, user: User): Promise<any> {
        let index = users.findIndex(x => x.id === id);
        if (index > -1) {
            users.splice(index, 1);
            users.push(user);
        }
    },

    removeUserByUsername: async function (username: string): Promise<boolean> {
        let temp = localStorage.getItem('token');
        if (temp === null) {
            temp = "";
        }
        let token: string = temp;


        const requestOptions = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + token
            },
            body: JSON.stringify({username: username})
        };
        let ret = false;
        await fetch('/removeUser', requestOptions)
            .then(async response => {
                if (response.status !== 200) {
                    return Promise.reject(response);
                } else {
                    ret = true;
                }
            }).catch(error => {
                console.error('There was an error!', error);
            })
        return ret;
    },

    getUsername() {
        return localStorage.getItem('username');
    }


}

export default API
