import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RechercheAvisComponent } from './recherche-avis.component';

describe('RechercheAvisComponent', () => {
  let component: RechercheAvisComponent;
  let fixture: ComponentFixture<RechercheAvisComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RechercheAvisComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RechercheAvisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
