import { GLTFLoader } from 'GLTFLoader';
import * as THREE from 'three';
import { OrbitControls } from 'OrbitControls';

// Scene, Renderer, Camera 설정
let scene = new THREE.Scene();
let renderer = new THREE.WebGLRenderer({
  canvas: document.querySelector('#canvas'),
  antialias: true
});
renderer.setSize(200, 200); // 캔버스 사이즈와 일치
renderer.outputEncoding = THREE.sRGBEncoding;

let camera = new THREE.PerspectiveCamera(30, 1, 0.1, 1000);
camera.position.set(0, 0, 1.8);

scene.background = new THREE.Color('white');

// GLTFLoader 설정
let loader = new GLTFLoader();
let model; // 로드된 모델을 담을 변수

loader.load('../../img/shiba/scene.gltf', (gltf) => {
      model = gltf.scene; // 모델을 변수에 저장
      scene.add(model);
      console.log('Model loaded:', model); // 모델 로드 확인
    },
    undefined,
    (error) => {
      console.error('An error happened', error);
    });

// OrbitControls 설정
let controls = new OrbitControls(camera, renderer.domElement);
controls.enableDamping = true; // 부드러운 카메라 이동
controls.dampingFactor = 0.25; // 이동 속도 조절
controls.enableZoom = true; // 줌 기능 활성화
controls.enablePan = true; // 팬 기능 활성화
controls.enableRotate = true; // 회전 기능 활성화

// 애니메이션 루프
function animate() {
  requestAnimationFrame(animate);

  // OrbitControls 업데이트
  controls.update();

  if (model) {
    renderer.render(scene, camera);
  }
}

animate(); // 애니메이션 시작