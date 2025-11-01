import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WebgpuBox } from './webgpu-box';

describe('WebgpuBox', () => {
  let component: WebgpuBox;
  let fixture: ComponentFixture<WebgpuBox>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WebgpuBox]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WebgpuBox);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
