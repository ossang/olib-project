import { Injectable } from '@angular/core';
import { Http }       from '@angular/http';
import { Rss }        from './rss.model';

@Injectable()
export class RssService {

  constructor(public http: Http) { }

  getRss(bookmarkId) : Promise<Rss[]>{
    return this.http.get('/api/rss/'+bookmarkId)
              .toPromise()
              .then(response => response.json() as Rss[])
              .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    return Promise.reject(error.message || error);
  }

}
