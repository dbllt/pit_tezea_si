interface Request {
    id: string;
    task: string;
    address: string;
}

interface Client {
    id: string;
    name: string,
    mail: string
}

interface User {
    id: string;
    username: string;
    role: string;
}


const serge: User = {
    id: "0",
    username: "serge",
    role: "serge"
}

const pierre: User = {
    id: "1",
    username: "pierre",
    role: "concierge"
}

const paul: Client = {
    id: "0",
    name: "paul",
    mail: "paul@mail.fr"
}


const request: Request = {
    id: "0",
    task: "task",
    address: "address"
}

let requests: Request[] = [];
let clients: Client[] = [];
let users: User[] = [];
users.push(serge);
users.push(pierre);
clients.push(paul);
requests.push(request);
const role="";

const API = {


    login: async function (username: string, password: string): Promise<boolean> {
        var found = false;

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
                    found = true;
                }
            }).catch(error => {
                console.error('There was an error!', error);
            })
        return found;
    },

    getRole: function (): string {
        return role;
    },

    getRequests: async function (): Promise<Request[]> {
        return requests;
    },
    addRequest: async function (task: string, address: string): Promise<any> {
        const request: Request = {
            id: clients.length.toString(),
            task: task,
            address: address
        };

        requests.push(request);
    },
    getRequest: async function (id: string): Promise<any> {
        requests.forEach(function (request) {
            if (request.id === id) {
                return request;
            }
        });
        return false;
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
        return users;
    },

    addUser: async function (username: string, password: string, role: string): Promise<any> {

        let temp = localStorage.getItem('token');
        if(temp===null){
            temp="";
        }
        let token: string = temp;
        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json',
                      'Authorization': "Bearer " +token},
            body: JSON.stringify({username: username, password: password})
        };

        await fetch('/register', requestOptions)
            .then(async response => {
                if (!response.ok) {
                    return Promise.reject(response);
                } else {
                    console.log('user created');
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

    removeUser: async function (id: string): Promise<any> {
        let index = users.findIndex(x => x.id === id);
        if (index > -1) {
            users.splice(index, 1);
        }
    },


}

export default API