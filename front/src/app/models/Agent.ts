import { v4 as uuidv4 } from 'uuid';

export class Agent{
    id: string = uuidv4();
    nom: string = '';
    prenom: string = '';
}