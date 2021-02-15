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

interface Utilisateur {
    id: string;
    identifiant: string;
    role: string;
}


const serge: Utilisateur = {
    id: "0",
    identifiant: "serge",
    role: "serge"
}

const pierre: Utilisateur = {
    id: "1",
    identifiant: "pierre",
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
let utilisateurs: Utilisateur[] = [];
utilisateurs.push(serge);
utilisateurs.push(pierre);
clients.push(paul);
requests.push(request);

let role="";

const API = {

    demoCallAPI: async function (): Promise<string> {
        let res: string = "";
        await fetch("https://pokeapi.co/api/v2/pokemon/ditto")
            .then(res => res.json()).then((data) => {
                res = data["id"]
            }).catch(console.log);
        return res;
    },


    login: async function (id: string, password: string): Promise<boolean> {
        var found=false;
        utilisateurs.forEach(utilisateur => {
            if (utilisateur.identifiant === id) {
                role=utilisateur.role;
                found=true;
            }
        });
        return found;
    },

    getRole: function():string{
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


    getUtilisateurs: async function (): Promise<Utilisateur[]> {
        return utilisateurs;
    },

    addUtilisateur: async function (identifiant: string, role: string): Promise<any> {
        const utilisateur: Utilisateur = {
            id: utilisateurs.length.toString(),
            identifiant: identifiant,
            role: role
        };

        utilisateurs.push(utilisateur);
    },

    getUtilisateur: async function (id: string): Promise<any> {
        utilisateurs.forEach(function (utilisateur) {
            if (utilisateur.id === id) {
                return utilisateur;
            }
        });
        return false;
    },

    editUtilisateur: async function (id: string, utilisateur: Utilisateur): Promise<any> {
        let index = utilisateurs.findIndex(x => x.id === id);
        if (index > -1) {
            utilisateurs.splice(index, 1);
            utilisateurs.push(utilisateur);
        }
    },

    removeUtilisateur: async function (id: string): Promise<any> {
        let index = utilisateurs.findIndex(x => x.id === id);
        if (index > -1) {
            utilisateurs.splice(index, 1);
        }
    },


}

export default API